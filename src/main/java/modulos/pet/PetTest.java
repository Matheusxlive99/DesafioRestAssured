package modulos.pet;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Testes de API Rest do modulo PET")
public class PetTest {

    @Test
    @DisplayName("Pesquisar por um PET inexistente")
    public void testPesquisarPetInexistente(){

        baseURI = "https://petstore.swagger.io/";
        basePath = "/v2";

        given()
                .when()
                    .get("/pet/0")
                .then()
                    .assertThat()
                         .body("message", equalTo("Pet not found"))
                              .statusCode(404);
    }

    @Test
    @DisplayName("Atualizar dados de um pet existente")
    public void testAtualizarDadosDePetExistente(){
        //Configurando os dados da api rest da petstore
        baseURI = "https://petstore.swagger.io/";
        basePath = "/v2";
        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": 0,\n" +
                        "  \"category\": {\n" +
                        "    \"id\": 0,\n" +
                        "    \"name\": \"string\"\n" +
                        "  },\n" +
                        "  \"name\": \"novo nome\",\n" +
                        "  \"photoUrls\": [\n" +
                        "    \"string\"\n" +
                        "  ],\n" +
                        "  \"tags\": [\n" +
                        "    {\n" +
                        "      \"id\": 0,\n" +
                        "      \"name\": \"novo nome\"\n" +
                        "    }\n" +
                        "  ],\n" +
                        "  \"status\": \"available\"\n" +
                        "}")
                .when()
                    .put("/pet")
                .then()
                    .assertThat()
                .body("name", equalTo("novo nome"))
                        .statusCode(200);
    }


    @Test
    @DisplayName("Pesquisar por pets com status pending ")
    public void testPesquisarPetStatusPending(){
        //Configurando os dados da api rest da petstore
        baseURI = "https://petstore.swagger.io/";
        basePath = "/v2";

        given()
                .when()
                    .get("/pet/findByStatus?status=pending")
                .then()
                    .assertThat()
                         .statusCode(200);

    }

}

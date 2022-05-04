package modulos.store;

import io.restassured.http.ContentType;
import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import static io.restassured.RestAssured.*;
import static io.restassured.matcher.RestAssuredMatchers.*;
import static org.hamcrest.Matchers.*;

@DisplayName("Teste de API Rest do modulo store")
public class StoreTest {

    @Test
    @DisplayName("Cadastrar novo pedido de pet com sucesso")
    public void testCadastrarPedidoPetSucesso(){

        baseURI = "https://petstore.swagger.io/";
        basePath = "/v2";

        given()
                .contentType(ContentType.JSON)
                .body("{\n" +
                        "  \"id\": 5,\n" +
                        "  \"petId\": 22,\n" +
                        "  \"quantity\": 3,\n" +
                        "  \"shipDate\": \"2022-05-04T04:38:03.198Z\",\n" +
                        "  \"status\": \"placed\",\n" +
                        "  \"complete\": true\n" +
                        "}")
                .when()
                    .post("/store/order")
                .then()
                    .assertThat()
                        .statusCode(200);
    }
}

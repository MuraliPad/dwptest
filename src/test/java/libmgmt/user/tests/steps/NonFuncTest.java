package libmgmt.user.tests.steps;


import io.cucumber.java.en.Given;
import io.restassured.module.jsv.JsonSchemaValidator;
import libmgmt.user.tests.context.Token;

import libmgmt.utils.PropReader;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.fail;
import io.restassured.response.Response;

public class NonFuncTest {
    private Response response;
    @Given("^Schema of the all book output validated$")
    public void schemaTest_allbook() {

        try {
                     given()
                    .baseUri(PropReader.getProperty("libr.baseurl"))
                    .header("Authorization",Token.getAuthToken())
                    .when()
                    .get(PropReader.getProperty("libr.listallbooks"))
                    .then()
                    .assertThat()
                            .statusCode(200)
                                    .body(JsonSchemaValidator.matchesJsonSchemaInClasspath("books-schema.json"));

        } catch (Exception e) {
          e.printStackTrace();
        }

    }
}

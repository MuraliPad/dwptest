package libmgmt.user.tests.steps;

import io.cucumber.java.en.And;
import io.restassured.response.Response;
import libmgmt.user.tests.context.Token;
import libmgmt.utils.PropReader;
import org.junit.Assert;

import static io.restassured.RestAssured.given;
import static org.junit.Assert.fail;

public class BookEnquiry {
    private Response response;
    @And("I wanted to search availability of the {string}")
    public void iWantedToSearchAvaiablityOfThe(String arg0) {
        try {

            response = given()
                    .baseUri(PropReader.getProperty("libr.baseurl"))
                    .header("Authorization",Token.getAuthToken())
                    .when()
                    .pathParams("title",arg0)
                    .queryParam("title",arg0)
                    .get(PropReader.getProperty("libr.listavail"))
                    .then()
                    .extract()
                    .response();
            Assert.assertEquals(200, response.getStatusCode());
            Assert.assertTrue(response.getBody().print().contains(arg0));


        } catch (Exception e) {
            fail("Failed to search book by author "+ e.getMessage());
        }
    }

    @And("I wanted to search for available books by author {string}")
    public void iWantedToSearchForAvailableBooksBy(String arg0) {
        try {

            response = given()
                    .baseUri(PropReader.getProperty("libr.baseurl"))
                    .header("Authorization",Token.getAuthToken())
                    .when()
                    .get(PropReader.getProperty("libr.listbyauthor") + arg0)
                    .then()
                    .extract()
                    .response();
            Assert.assertEquals(200, response.getStatusCode());
            Assert.assertTrue(response.getBody().print().contains(arg0));

        } catch (Exception e) {
            fail("Failed to search book by author "+ e.getMessage());


        }
    }

    @And("I wanted to search for book {string}")
    public void iWantedToSearchFor(String arg0) {

        try {

            response = given()
                    .baseUri(PropReader.getProperty("libr.baseurl"))
                    .header("Authorization",Token.getAuthToken())
                    .when()
                    .get(PropReader.getProperty("libr.listbytitle") + arg0)
                    .then()
                    .extract()
                    .response();
            Assert.assertEquals(200, response.getStatusCode());
            Assert.assertTrue(response.getBody().print().contains(arg0));

        } catch (Exception e) {
            fail("Failed to search book by title "+ e.getMessage());


        }


    }
}

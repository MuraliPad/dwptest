package libmgmt.user.tests.steps;

import com.fasterxml.jackson.databind.ObjectMapper;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import io.restassured.response.Response;
import libmgmt.user.tests.context.Token;
import libmgmt.user.tests.models.Book;
import libmgmt.user.tests.models.BookQuery;
import libmgmt.utils.PropReader;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.util.List;
import java.util.stream.Collectors;

import static io.restassured.RestAssured.given;
import static libmgmt.user.tests.steps.BorrowAndReturn.allBooks;
import static org.junit.Assert.fail;

public class NegativeScenarios {

    private List<Book> nonAvailableBooks;
    private ObjectMapper objectMapper = new ObjectMapper();
    private Response response;

    private String bookTitle;
    @When("^I select book which is not available$")
    public void ichooseanynonavailbook() {
        nonAvailableBooks = allBooks.stream().filter(book -> book.getAvailableCopies() == 0).collect(Collectors.toList());
        bookTitle = nonAvailableBooks.get(1).getTitle();
        Assert.assertFalse("No books available",bookTitle.isEmpty() | bookTitle.isBlank());
        Assert.assertTrue(nonAvailableBooks.get(1).getAvailableCopies() == 0);
    }

    @Then("^system should handle properly$")
    public void iCouldabletoborrowSuccesfully() {

        BookQuery bookQuery = new BookQuery(PropReader.getProperty("libr.username"),bookTitle);

        try {
            String bookQuery_json = objectMapper.writeValueAsString(bookQuery);
            response = given()
                    .baseUri(PropReader.getProperty("libr.baseurl"))
                    .header("Authorization", "Bearer " + Token.getAuthToken())
                    .contentType("application/json")
                    .body(bookQuery_json)
                    .when()
                    .post(PropReader.getProperty("libr.borrow"))
                    .then()
                    .extract()
                    .response();
            Assert.assertEquals(400, response.getStatusCode());

        } catch (Exception e) {
            fail("Unable to borrow book  "+ bookTitle + e.getMessage());
        }

    }

}

package libmgmt.user.tests.steps;
import com.fasterxml.jackson.core.type.TypeReference;
import io.cucumber.cienvironment.internal.com.eclipsesource.json.JsonObject;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;
import static org.junit.Assert.assertArrayEquals;
import static org.junit.Assert.*;

import libmgmt.user.tests.context.Token;
import libmgmt.user.tests.models.Book;
import libmgmt.user.tests.models.BookQuery;
import libmgmt.user.tests.models.UserLogin;
import libmgmt.utils.PropReader;

import com.fasterxml.jackson.databind.ObjectMapper;
import org.json.simple.JSONObject;
import org.junit.Assert;

import java.util.List;
import java.util.stream.Collectors;
import org.json.simple.parser.*;
public class BorrowAndReturn {
    private Response response;
    private String token;
    public static List<Book> allBooks;
    private List<Book> availableBooks;
    private String bookTitle;
    private ObjectMapper objectMapper = new ObjectMapper();
     private JSONParser parser = new JSONParser();


    @Given("I could login to library management successfully")
    public void iCouldLoginToLibraryManagmentSuccesfully() {

        UserLogin userLogin = new UserLogin(PropReader.getProperty("libr.username"),PropReader.getProperty("libr.password"));
        try {
            String userLogin_json = objectMapper.writeValueAsString(userLogin);
            response = given()
                    .baseUri(PropReader.getProperty("libr.baseurl"))
                    .contentType("application/json")
                    .body(userLogin_json)
                    .when()
                    .post(PropReader.getProperty("libr.userlogin"))
                    .then()
                    .extract()
                    .response();
            Assert.assertEquals(200, response.getStatusCode());
            token = response.jsonPath().getString("token");

            Assert.assertNotNull("Token is not empty",token);
            Token.setAuthToken(token);
            System.out.println(Token.getAuthToken());

        } catch (Exception e) {
            fail("Failed to login "+ e.getMessage());
        }

    }

    @And("^I could able to list all the books$")
    public void iCouldableTotolistallthebooks() {

        response = given()
                .baseUri(PropReader.getProperty("libr.baseurl"))
                .header("Authorization",Token.getAuthToken())
                .when()
                .get(PropReader.getProperty("libr.listallbooks"))
                .then()
                .extract()
                .response();
        Assert.assertEquals(200, response.getStatusCode());
        try {
            allBooks = objectMapper.readValue(response.getBody().print(), new TypeReference<List<Book>>() {});

            Assert.assertFalse("Book list should not be empty",allBooks.isEmpty());
        } catch (Exception e) {
            e.printStackTrace();
        }
    }
    @Then("^I choose any book randomly which is available$")
    public void ichooseanybookrandomly() {
        availableBooks = allBooks.stream().filter(book -> book.getAvailableCopies() > 1).collect(Collectors.toList());
        bookTitle = availableBooks.get(1).getTitle();
        Assert.assertFalse("No books available",bookTitle.isEmpty() | bookTitle.isBlank());
        System.out.println("Book Title " + bookTitle);
    }
    @And("^I could able to borrow successfully$")
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
            Assert.assertEquals(201, response.getStatusCode());
            String  bookBorrowed =  response.getBody().print();
            JSONObject bookBorrowed_json = (JSONObject) parser.parse(bookBorrowed);
            Assert.assertTrue(bookBorrowed_json.get("message").equals("Book borrowed successfully"));
            System.out.println("Borrow Book " + response.getBody().print());
        } catch (Exception e) {
            fail("Unable to borrow book  "+ bookTitle + e.getMessage());
        }

    }
    @Then("^I could able to return successfully$")
    public void iCouldabletoreturnSuccesfully() {


        BookQuery bookQuery = new BookQuery(PropReader.getProperty("libr.username"),bookTitle);

        try {
            String bookQuery_json = objectMapper.writeValueAsString(bookQuery);
            response = given()
                    .baseUri(PropReader.getProperty("libr.baseurl"))
                    .header("Authorization","Bearer " + Token.getAuthToken())
                    .contentType("application/json")
                    .body(bookQuery_json)
                    .when()
                    .post(PropReader.getProperty("libr.return"))
                    .then()
                    .extract()
                    .response();
            Assert.assertEquals(200, response.getStatusCode());
            String  bookReturned =  response.getBody().print();
            JSONObject bookReturned_json = (JSONObject) parser.parse(bookReturned);
            Assert.assertTrue(bookReturned_json.get("message").equals("Book returned successfully"));
        } catch (Exception e) {
            fail("Unable to return book  "+ bookTitle + e.getMessage());
        }
    }

    @And("I could able to view borrowing history")
    public void iCouldAbleToViewBorrowingHistory() {
        JsonObject userJson = new JsonObject();
        userJson.add("username", PropReader.getProperty("libr.username"));

        try {

            response = given()
                    .baseUri(PropReader.getProperty("libr.baseurl"))
                    .header("Authorization",  "Bearer " + Token.getAuthToken())
                    .contentType("application/json")
                    .body(userJson)
                    .when()
                    .post(PropReader.getProperty("libr.borrowhistory"))
                    .then()
                    .extract()
                    .response();
            Assert.assertEquals(200, response.getStatusCode());
            System.out.println("Books Borrow History " + response.getBody().print());
        } catch (Exception e) {
            fail("Unable to return book  "+   e.getMessage());
        }

    }


}

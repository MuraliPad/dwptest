package libmgmt.user.tests;
import com.fasterxml.jackson.core.JsonProcessingException;
import io.cucumber.java.Before;
import io.cucumber.java.After;
import libmgmt.user.tests.context.Token;
import libmgmt.user.tests.models.UserLogin;
import libmgmt.utils.PropReader;
import org.junit.Assert;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.restassured.response.Response;
import static io.restassured.RestAssured.given;

public class GlobalHooks {
    private ObjectMapper objectMapper = new ObjectMapper();
    private Response response;
    public static String user_token;
    @Before
    public void setup() {
      Token.setAuthToken(null);

    }
    @After
    public void teardown() {
        Token.setAuthToken(null);
    }
}

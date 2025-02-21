package libmgmt.user.tests.context;

public class Token {

    private static String authToken;


    public static void setAuthToken(String token) {
        authToken = token;
    }


    public static java.lang.String getAuthToken() {
        return authToken;
    }
}
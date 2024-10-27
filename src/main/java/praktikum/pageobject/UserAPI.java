package praktikum.pageobject;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import praktikum.AccessToken;

import static io.restassured.RestAssured.given;

public class UserAPI {
    final static String USERREGISTERED = "/api/auth/register";
    final static String USERDATA = "/api/auth/userData";
    final static String USERLOGIN = "/api/auth/login";
    final static String USERLOGOUT = "api/auth/logout";

    public static Response createUser(UserData userData) {

        Response response = given()
                .header("Content-type", "application/json")
                .body(userData)
                .post(USERREGISTERED);
        return response;
    }

    public static Response authorizedUser(UserData userData){

        Response response = given()
                .header("Content-type", "application/json")
                .body(userData)
                .post(USERLOGIN);
        return response;
    }

    public static String getToken (UserData userData){
        String token = UserAPI.authorizedUser(userData).body().asString();
        String[] split = token.split(" ");
        String tokenNumber = split[1].substring(0, 171);
        return tokenNumber;
    }

    public static Response deleteUser(String token, UserData userData){

        Response response = given()
                .header("Content-type", "application/json")
                .auth().oauth2(token)
                .body(userData)
                .delete(USERDATA);
        return response;
    }


    public static ValidatableResponse makeLogout(String token){
        AccessToken refreshToken = new AccessToken(token);

        return given()
                .header("Content-type", "application/json")
                .body(refreshToken)
                .post(USERLOGOUT)
                .then();
    }

}
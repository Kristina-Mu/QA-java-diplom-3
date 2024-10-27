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

    @Step("Выполняем POST-запрос для создания пользователя")
    public static Response createUser(UserData userData) {

        Response response = given()
                .header("Content-type", "application/json")
                .body(userData)
                .post(USERREGISTERED);
        return response;
    }

    @Step("Выполняем POST-запрос для авторизации пользователя")
    public static Response authorizedUser(UserData userData){

        Response response = given()
                .header("Content-type", "application/json")
                .body(userData)
                .post(USERLOGIN);
        return response;
    }

    @Step("Получаем токен пользователя")
    public static String getToken (UserData userData){
        String token = UserAPI.authorizedUser(userData).body().asString();
        String[] split = token.split(" ");
        String tokenNumber = split[1].substring(0, 171);
        return tokenNumber;
    }

    @Step("Выполняем DELETE-запрос для удаления пользователя")
    public static Response deleteUser(String token, UserData userData){

        Response response = given()
                .header("Content-type", "application/json")
                .auth().oauth2(token)
                .body(userData)
                .delete(USERDATA);
        return response;
    }


    @Step("Выполняем запрос для выхода из системы")
    public static ValidatableResponse makeLogout(String token){
        AccessToken refreshToken = new AccessToken(token);

        return given()
                .header("Content-type", "application/json")
                .body(refreshToken)
                .post(USERLOGOUT)
                .then();
    }

}
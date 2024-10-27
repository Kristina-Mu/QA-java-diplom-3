package praktikum;

import io.qameta.allure.Step;
import io.restassured.response.Response;
import io.restassured.response.ValidatableResponse;
import praktikum.pageobject.UserData;

import static io.restassured.RestAssured.given;

public class UserAPI {
    final static String BASE_URL = "http://your.api.url"; // Установите правильный базовый URL API
    final static String USERREGISTERED = BASE_URL + "/api/auth/register";
    final static String USERDATA = BASE_URL + "/api/auth/userData";
    final static String USERLOGIN = BASE_URL + "/api/auth/login";
    final static String USERLOGOUT = BASE_URL + "/api/auth/logout";

    @Step("Выполняем POST-запрос для создания пользователя")
    public static Response createUser(UserData userData) {
        return given()
                .header("Content-type", "application/json")
                .body(userData)
                .post(USERREGISTERED);
    }

    @Step("Выполняем POST-запрос для авторизации пользователя")
    public static Response authorizedUser(UserData userData) {
        return given()
                .header("Content-type", "application/json")
                .body(userData)
                .post(USERLOGIN);
    }

    @Step("Получаем токен пользователя")
    public static String getToken(UserData userData) {
        Response response = authorizedUser(userData);
        // Убедитесь, что в ответе есть токен
        if (response.getStatusCode() == 200) {
            String token = response.body().jsonPath().getString("token"); // Пример извлечения токена
            return token;
        }
        throw new RuntimeException("Не удалось получить токен, ответ: " + response.getStatusLine());
    }

    @Step("Выполняем DELETE-запрос для удаления пользователя")
    public static Response deleteUser(String token, UserData userData) {
        return given()
                .header("Content-type", "application/json")
                .auth().oauth2(token)
                .body(userData)
                .delete(USERDATA);
    }

    @Step("Выполняем запрос для выхода из системы")
    public static ValidatableResponse makeLogout(String token) {
        AccessToken refreshToken = new AccessToken(token); // Убедитесь, что класс AccessToken определен

        return given()
                .header("Content-type", "application/json")
                .body(refreshToken)
                .post(USERLOGOUT)
                .then();
    }
}

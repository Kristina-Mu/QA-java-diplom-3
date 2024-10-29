package praktikum;

import io.qameta.allure.Step;
import org.junit.Test;
import praktikum.pageobject.*;

import static org.junit.Assert.assertEquals;

public class RegistrationTest extends DriverTest {

    String emailReg = "1oQRtq4@yandex.ru";
    String emailIncReg = "YYYYYYYY3@yandex.ru";
    String passwordReg = "111111";
    String nameReg = "Надежда";
    UserData userDataReg = new UserData(emailReg, passwordReg, nameReg);
    String incorrectPasswordReg = "00000";

    @Test
    @Step("Проверяем успешную регистрацию")
    public void checkRegistrationSuccessfully() {
        HomePageObject headPage = new HomePageObject(driver);
        AuthorizationPageObject authorization = new AuthorizationPageObject(driver);
        RegistrationPageObject registration = new RegistrationPageObject(driver);

        // Нажимаем на ссылку "Личный кабинет" и авторизуемся
        headPage.clickOnLinkToPersonalArea();

        // Нажимаем на ссылку "Зарегистрироваться"
        authorization.clickTheLinkToRegistrationPageFromAuthorizationPage();

        // Заполняем поля формы
        registration.emailFieldFillingThroughRegistration(emailReg);
        registration.nameFieldFillingThroughRegistration(nameReg);
        registration.passwordFieldFillingThroughRegistration(passwordReg);
        registration.clickRegistrationButton();

        // Проверяем, что после регистрации попадаем на страницу авторизации с названием блока "Вход"
        authorization.waitNameTopicVisibility();
        assertEquals("Пользователь не прошел авторизацию",
                "Забыли пароль? Восстановить пароль",
                authorization.getTextFromQuestionOnAuthorizationForm());

        // Авторизуемся
        authorization.fillingTheEmailField(emailReg);
        authorization.fillingThePasswordField(passwordReg);

        // И удаляем пользователя
        UserAPI.makeLogout(UserAPI.getToken(userDataReg));
        UserAPI.deleteUser(UserAPI.getToken(userDataReg), userDataReg);
    }

    @Test
    @Step("Проверяем сообщение об ошибке при некорректном пароле")
    public void checkErrorMessageIfPasswordIncorrect() {
        HomePageObject headPage = new HomePageObject(driver);
        AuthorizationPageObject authorization = new AuthorizationPageObject(driver);
        RegistrationPageObject registration = new RegistrationPageObject(driver);

        // Нажимаем на ссылку "Личный кабинет" и авторизуемся
        headPage.clickOnLinkToPersonalArea();

        // Нажимаем на ссылку "Зарегистрироваться"
        authorization.clickTheLinkToRegistrationPageFromAuthorizationPage();

        // Заполняем поля формы
        registration.emailFieldFillingThroughRegistration(emailIncReg);
        registration.nameFieldFillingThroughRegistration(nameReg);
        registration.passwordFieldFillingThroughRegistration(incorrectPasswordReg);
        registration.clickRegistrationButton();

        // Проверяем сообщение об ошибке при пароле менее 6 символов
        assertEquals("Не выдержано требование к минимальности пароля",
                "Некорректный пароль",
                registration.getErrorMessageWhenIncorrectPassword());
    }
}
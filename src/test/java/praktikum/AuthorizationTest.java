package praktikum;

import io.qameta.allure.Step;
import org.junit.Test;
import praktikum.pageobject.AuthorizationPageObject;
import praktikum.pageobject.HomePageObject;
import praktikum.pageobject.RecoveryPageObject;
import praktikum.pageobject.RegistrationPageObject;

import static org.junit.Assert.assertEquals;

public class AuthorizationTest extends DriverTest {

    @Test
    @Step("Проверяем вход через кнопку 'Войти в аккаунт'")
    public void checkLoginViaEnterInAccountButton() {
        HomePageObject headPage = new HomePageObject(driver);
        AuthorizationPageObject authorization = new AuthorizationPageObject(driver);

        // Нажимаем на кнопку "Войти в аккаунт" и авторизуемся
        headPage.clickEnterInAccountButton();

        authorization.fillingTheEmailField(email);
        authorization.fillingThePasswordField(password);

        // Проверяем, что после авторизации на главной странице появляется кнопка "Оформить заказ"
        headPage.waitOrderButtonVisibility();
        assertEquals("Пользователь не прошел авторизацию",
                "Оформить заказ",
                headPage.getTextFromOrderButton());
    }

    @Test
    @Step("Проверяем вход через 'Личный кабинет'")
    public void checkLoginViaPersonalArea() {
        HomePageObject headPage = new HomePageObject(driver);
        AuthorizationPageObject authorization = new AuthorizationPageObject(driver);

        // Нажимаем на ссылку "Личный кабинет" и авторизуемся
        headPage.clickOnLinkToPersonalArea();

        authorization.fillingTheEmailField(email);
        authorization.fillingThePasswordField(password);

        // Проверяем, что после авторизации на главной странице появляется кнопка "Оформить заказ"
        headPage.waitOrderButtonVisibility();
        assertEquals("Пользователь не прошел авторизацию",
                "Оформить заказ",
                headPage.getTextFromOrderButton());
    }

    @Test
    @Step("Проверяем вход через форму регистрации")
    public void checkLoginViaRegistrationForm() {
        HomePageObject headPage = new HomePageObject(driver);
        AuthorizationPageObject authorization = new AuthorizationPageObject(driver);
        RegistrationPageObject registrationForm = new RegistrationPageObject(driver);

        // Нажимаем на ссылку "Личный кабинет"
        headPage.clickOnLinkToPersonalArea();

        // На форме авторизации нажимаем линку-переход к форме регистрации
        authorization.clickTheLinkToRegistrationPageFromAuthorizationPage();

        // Переход с формы регистрации на форму авторизации по линке "Войти" и авторизуемся
        registrationForm.clickToLinkInRegistrationForm();

        authorization.fillingTheEmailField(email);
        authorization.fillingThePasswordField(password);

        // Проверяем, что после авторизации на главной странице появляется кнопка "Оформить заказ"
        headPage.waitOrderButtonVisibility();
        assertEquals("Пользователь не прошел авторизацию",
                "Оформить заказ",
                headPage.getTextFromOrderButton());
    }

    @Test
    @Step("Проверяем вход через форму восстановления пароля")
    public void checkLoginViaRecoveryPasswordForm() {
        HomePageObject headPage = new HomePageObject(driver);
        AuthorizationPageObject authorization = new AuthorizationPageObject(driver);
        RecoveryPageObject recoveryPage = new RecoveryPageObject(driver);

        // Нажимаем на ссылку "Личный кабинет"
        headPage.clickOnLinkToPersonalArea();

        // На форме авторизации нажимаем линку-переход к форме "Восстановить пароль"
        authorization.clickThePasswordRecoveryLink();

        // Переход с формы восстановления пароля на форму авторизации по линке "Войти"
        recoveryPage.clickEnterButtonFromRecoveryPasswordPage();

        authorization.fillingTheEmailField(email);
        authorization.fillingThePasswordField(password);

        // Проверяем, что после авторизации на главной странице появляется кнопка "Оформить заказ"
        headPage.waitOrderButtonVisibility();
        assertEquals("Пользователь не прошел авторизацию",
                "Оформить заказ",
                headPage.getTextFromOrderButton());
    }
}
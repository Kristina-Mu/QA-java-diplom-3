package praktikum;

import io.qameta.allure.junit4.DisplayName;
import org.junit.After;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.NoSuchElementException;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import praktikum.pageobject.MainPage;

import static org.apache.commons.lang3.RandomStringUtils.randomAlphabetic;
import static org.junit.Assert.assertTrue;

public class TransitionsTest {
    @Rule
    public LogInTest driverRule = new LogInTest();
    private WebDriver driver;
    private String nameUser;
    private String emailUser;
    private String passwordUser;
    private String accessToken;

    private MainPage mainPage;
    private Steps steps;

    @Before
    public void setup() {
        driver = driverRule.getDriver();
        driver.get("https://stellarburgers.nomoreparties.site/");

        nameUser = randomAlphabetic(12);
        emailUser = randomAlphabetic(8) + "@yandex.ru";
        passwordUser = randomAlphabetic(10);

        mainPage = new MainPage(driver);
        steps = new Steps(driver);

        accessToken = steps.createUser(nameUser, emailUser, passwordUser);
    }

    @After
    public void browserClose() {
        if (accessToken != null) {
            steps.deleteUser(accessToken);
        }
        driver.quit();
    }

    private void closeModalIfOpen() {
        try {
            WebElement modal = driver.findElement(By.className("Modal_modal_overlay__x2ZCr"));
            if (modal.isDisplayed()) {
                modal.click(); // Закрытый модал
            }
        } catch (NoSuchElementException e) {
            // Модальное окно не открыто
        }
    }

    @Test
    @DisplayName("Переход в личный кабинет")
    public void transitionToPersonalAccount() {
        closeModalIfOpen();
        steps.clickLoginButton(driver);
        closeModalIfOpen();
        steps.login(driver, emailUser, passwordUser);
        steps.clickPersonalAccount(driver);

        ProfilePage profilePage = new ProfilePage(driver);
        assertTrue(profilePage.findElementHeadOrder());
    }

    @Test
    @DisplayName("Переход из личного кабинета в конструктор")
    public void transitionFromPersonalAccountToConstructor() {
        closeModalIfOpen();
        steps.clickLoginButton(driver);
        closeModalIfOpen();
        steps.login(driver, emailUser, passwordUser);
        steps.clickPersonalAccount(driver);

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickConstructorButton();

        assertTrue(mainPage.findConstructorHead());
    }

    @Test
    @DisplayName("Переход из личного кабинета на логотип Stellar Burgers")
    public void transitionFromPersonalAccountOnLogo() {
        closeModalIfOpen();
        steps.clickLoginButton(driver);
        closeModalIfOpen();
        steps.login(driver, emailUser, passwordUser);
        steps.clickPersonalAccount(driver);

        ProfilePage profilePage = new ProfilePage(driver);
        profilePage.clickLogo();

        assertTrue(mainPage.findConstructorHead());
    }

    @Test
    @DisplayName("Переход к разделу «Булки»")
    public void transitionToRolls() {
        closeModalIfOpen();
        mainPage.clickSaucesButton();
        mainPage.clickRollsButton();
        assertTrue(mainPage.activeTab().equals("Булки"));
    }

    @Test
    @DisplayName("Переход к разделу «Соусы»")
    public void transitionToSauces() {
        closeModalIfOpen();
        mainPage.clickSaucesButton();
        assertTrue(mainPage.activeTab().equals("Соусы"));
    }

    @Test
    @DisplayName("Переход к разделу «Начинки»")
    public void transitionToFillings() {
        closeModalIfOpen();
        mainPage.clickFillingsButton();
        assertTrue(mainPage.activeTab().equals("Начинки"));
    }
}

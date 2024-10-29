package praktikum;

import io.qameta.allure.Step;
import org.junit.Test;
import praktikum.pageobject.AuthorizationPageObject;
import praktikum.pageobject.ConstructorPageObject;
import praktikum.pageobject.HomePageObject;
import praktikum.pageobject.PersonalAreaPageObject;

import static org.junit.Assert.assertEquals;
import org.openqa.selenium.By;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class PersonalAreaTest extends DriverTest {

    @Test
    @Step("Проверяем возможность доступа к личному кабинету")
    public void checkPossibilityToGetPersonalArea() {
        HomePageObject headPage = new HomePageObject(driver);
        AuthorizationPageObject authorization = new AuthorizationPageObject(driver);
        PersonalAreaPageObject personalArea = new PersonalAreaPageObject(driver);

        // Нажимаем на ссылку "Личный кабинет" и авторизуемся
        headPage.clickOnLinkToPersonalArea();
        authorization.fillingTheEmailField(email);
        authorization.fillingThePasswordField(password);

        // Нажимаем на ссылку "Личный кабинет"
        headPage.waitOrderButtonVisibility();
        headPage.clickOnLinkToPersonalArea();

        // Ждем загрузку странички
        personalArea.waitExitButtonVisibility();

        // Проверяем, что мы в профиле как зарегистрированный пользователь
        assertEquals("Пользователь не прошел авторизацию",
                "В этом разделе вы можете изменить свои персональные данные",
                personalArea.checkInformationBlock());
    }

    @Test
    @Step("Проверяем возможность выхода из личного кабинета")
    public void checkToGoAwayFromPersonalArea() {
        HomePageObject headPage = new HomePageObject(driver);
        AuthorizationPageObject authorization = new AuthorizationPageObject(driver);
        PersonalAreaPageObject personalArea = new PersonalAreaPageObject(driver);

        // Нажимаем на ссылку "Личный кабинет" и авторизуемся
        headPage.clickOnLinkToPersonalArea();
        authorization.fillingTheEmailField(email);
        authorization.fillingThePasswordField(password);

        // Нажимаем на ссылку "Личный кабинет"
        headPage.waitOrderButtonVisibility();
        headPage.clickOnLinkToPersonalArea();

        // Ждем, когда кнопка выхода станет доступной, ищем кнопку "Выход" и нажимаем на нее
        personalArea.waitExitButtonVisibility();
        personalArea.clickExitButtonFromPersonalArea();
        authorization.waitNameTopicVisibility();

        assertEquals("Пользователь не смог выйти из аккаунта",
                "Вход",
                authorization.getTextFromTopicName());
    }

    @Test
    @Step("Проверяем возможность перехода в конструктор")
    public void checkToMoveOnConstructorArea() {
        HomePageObject headPage = new HomePageObject(driver);
        AuthorizationPageObject authorization = new AuthorizationPageObject(driver);
        ConstructorPageObject constructorPage = new ConstructorPageObject(driver);

        // Нажимаем на ссылку "Личный кабинет" и авторизуемся
        headPage.clickOnLinkToPersonalArea();
        authorization.fillingTheEmailField(email);
        authorization.fillingThePasswordField(password);

        // Нажимаем на ссылку "Личный кабинет"
        headPage.waitOrderButtonVisibility();
        headPage.clickOnLinkToPersonalArea();

        // Явное ожидание перед переходом на конструктор
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/header/nav/ul/li[1]/a/p"))); // Замените XPath на актуальный

        // Нажимаем на элемент "Конструктор"
        constructorPage.clickToLinkToConstructor();

        // Проверяем наличие элемента "Соберите бургер"
        assertEquals("Пользователь не смог перейти в конструктор",
                "Соберите бургер",
                constructorPage.getTextFromConstructorTopic());
    }

    @Test
    @Step("Проверяем возможность перехода на главную страницу через логотип")
    public void checkToMoveOnMainLogo() {
        HomePageObject headPage = new HomePageObject(driver);
        AuthorizationPageObject authorization = new AuthorizationPageObject(driver);
        ConstructorPageObject constructorPage = new ConstructorPageObject(driver);

        // Нажимаем на ссылку "Личный кабинет" и авторизуемся
        headPage.clickOnLinkToPersonalArea();
        authorization.fillingTheEmailField(email);
        authorization.fillingThePasswordField(password);

        // Нажимаем на ссылку "Личный кабинет"
        headPage.waitOrderButtonVisibility();
        headPage.clickOnLinkToPersonalArea();

        // Явное ожидание перед кликом по логотипу
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        wait.until(ExpectedConditions.elementToBeClickable(By.xpath("//*[@id='root']/div/header/nav/div/a"))); // Обновите XPath на актуальный

        // Теперь выполняем клик по логотипу
        constructorPage.clickToStellarBurgersLogoLink();

        // Проверяем наличие элемента "Соберите бургер"
        assertEquals("Пользователь не смог перейти на главную страницу",
                "Соберите бургер",
                constructorPage.getTextFromConstructorTopic());
    }
}


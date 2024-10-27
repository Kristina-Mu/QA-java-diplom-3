package praktikum;

import io.qameta.allure.Step;
import org.junit.Test;
import praktikum.pageobject.ConstructorPageObject;
import praktikum.pageobject.HomePageObject;

import static org.junit.Assert.assertEquals;

public class ConstructorPageTest extends DriverTest {

    private final String expectedClass = "tab_tab__1SPyG tab_tab_type_current__2BEPc pt-4 pr-10 pb-4 pl-10 noselect"; // Ожидаемое значение класса

    @Test
    @Step("Проверяем, что щелчок по булкам активирует соответствующую вкладку")
    public void checkThatClickOnBunsMakeBunsThemeIsActive() {
        HomePageObject homePage = new HomePageObject(driver);
        ConstructorPageObject constructor = new ConstructorPageObject(driver);

        // Переходим на главную страницу и в раздел конструктора
        driver.get(HomePageObject.URL);
        homePage.clickOnLinkToPersonalArea(); // Здесь можно добавить авторизацию
        constructor.clickToLinkToConstructor();

        // Перейдем на вкладку начинок
        constructor.clickToFillingsMakeItActive();

        // Теперь перейдем в раздел булок и проверяем активный класс
        constructor.clickToBunMakeItActive();
        String actualClass = constructor.waitForClassToBecomeActive(constructor.getBunsTopicLocator()); // Локатор для булок

        assertEquals("Раздел булок не становится активным", expectedClass, actualClass);
    }

    @Test
    @Step("Проверяем, что щелчок по соусам активирует соответствующую вкладку")
    public void checkThatClickOnSousesMakeSouseThemeIsActive() {
        HomePageObject homePage = new HomePageObject(driver);
        ConstructorPageObject constructor = new ConstructorPageObject(driver);

        // Переходим на главную страницу и в раздел конструктора
        driver.get(HomePageObject.URL);
        homePage.clickOnLinkToPersonalArea(); // Здесь можно добавить авторизацию
        constructor.clickToLinkToConstructor();

        // Переходим в раздел соусов и проверяем активный класс
        String actualClass = constructor.clickToSousesMakeItActive();
        assertEquals("Раздел соусов не становится активным", expectedClass, actualClass);
    }

    @Test
    @Step("Проверяем, что щелчок по начинкам активирует соответствующую вкладку")
    public void checkThatFillingsOpensFillingsTheme() {
        HomePageObject homePage = new HomePageObject(driver);
        ConstructorPageObject constructor = new ConstructorPageObject(driver);

        // Переходим на главную страницу и в раздел конструктора
        driver.get(HomePageObject.URL);
        homePage.clickOnLinkToPersonalArea(); // Здесь можно добавить авторизацию
        constructor.clickToLinkToConstructor();

        // Переходим в раздел начинок и проверяем активный класс
        String actualClass = constructor.clickToFillingsMakeItActive();
        assertEquals("Раздел начинок не становится активным", expectedClass, actualClass);
    }
}
package praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.DriverClass;

import java.time.Duration;

public class HomePageObject extends DriverClass {
    public static final String URL = "https://stellarburgers.nomoreparties.site";
    private final By linkToPersonalArea = By.xpath("//*[@id='root']/div/header/nav/a/p");
    private final By toCreatedOrderButton = By.xpath("//*[@id='root']/div/main/section[2]/div/button");
    private final By enterInAccountButton = By.xpath("//*[@id='root']/div/main/section[2]/div/button");

    public HomePageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Нажимаем на ссылку 'Личный кабинет'")
    public void clickOnLinkToPersonalArea() {
        WebElement wb = driver.findElement(linkToPersonalArea);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", wb);
    }

    @Step("Нажимаем на кнопку 'Войти в аккаунт'")
    public void clickEnterInAccountButton() {
        WebElement wb = driver.findElement(enterInAccountButton);
        JavascriptExecutor jse = (JavascriptExecutor) driver;
        jse.executeScript("arguments[0].click();", wb);
    }

    @Step("Ждем, пока кнопка 'Оформить заказ' станет видимой")
    public void waitOrderButtonVisibility() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(toCreatedOrderButton));
    }

    @Step("Получаем текст с кнопки для входа в аккаунт")
    public String getTextFromOrderButton() {
        return driver.findElement(toCreatedOrderButton).getText();
    }
}

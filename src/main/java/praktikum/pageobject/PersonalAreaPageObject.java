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

public class PersonalAreaPageObject extends DriverClass {
    private final By informationBlock = By.xpath("//*[@id='root']/div/main/div/nav/p");
    private final By exitButton = By.xpath("//*[@id='root']/div/main/div/nav/ul/li[3]/button");

    public PersonalAreaPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Нажимаем кнопку для выхода из личного кабинета")
    public void clickExitButtonFromPersonalArea() {
        WebElement wb = driver.findElement(exitButton);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click();", wb);
    }

    @Step("Получаем текст информации из личного кабинета")
    public String checkInformationBlock() {
        WebElement wb = driver.findElement(informationBlock);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click();", wb); // Необязательный клик здесь: если это только для получения текста, можно убрать
        return wb.getText();
    }

    @Step("Ждем, пока кнопка выхода из личного кабинета станет видимой")
    public void waitExitButtonVisibility() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(exitButton));
    }
}

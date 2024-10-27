package praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import java.time.Duration;

public class PersonalAreaPageObject extends site.stellarburgers.pageobject.DriverClass {
    private final By informationBlock = By.xpath("//*[@id='root']/div/main/div/nav/p");
    private final By exitButton = By.xpath("//*[@id='root']/div/main/div/nav/ul/li[3]/button");

    public PersonalAreaPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Make click button to exit from personal area")
    public void clickExitButtonFromPersonalArea() {
        WebElement wb = driver.findElement(exitButton);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click();", wb);
    }

    @Step("Get information text from personal area")
    public String checkInformationBlock() {

        WebElement wb = driver.findElement(informationBlock);
        JavascriptExecutor jse = (JavascriptExecutor)driver;
        jse.executeScript("arguments[0].click();", wb);
        return wb.getText();
    }

    @Step("Wait until button to exit from personal area will be visible")
    public void waitExitButtonVisibility() {
        new WebDriverWait(driver, Duration.ofSeconds(30))
                .until(ExpectedConditions.visibilityOfElementLocated(exitButton));
    }

}
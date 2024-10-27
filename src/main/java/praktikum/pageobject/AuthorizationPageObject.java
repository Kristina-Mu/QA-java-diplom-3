package praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.*;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.DriverClass;

import java.time.Duration;

public class AuthorizationPageObject extends DriverClass {
    private final By authorizationEmail = By.xpath("//*[@id='root']/div/main/div/form/fieldset[1]/div/div/input");
    private final By authorizationPassword = By.xpath("//*[@id='root']/div/main/div/form/fieldset[2]/div/div/input");
    private final By linkToRegistrationPageFromAuthorizationPage = By.xpath("//*[@id='root']/div/main/div/div/p[1]/a");
    private final By passwordRecoveryLink = By.xpath("//*[@id='root']/div/main/div/div/p[2]/a");
    private final By nameTopic = By.xpath("//*[@id='root']/div/main/div/h2");
    private final By questionOnAuthorizationPage = By.xpath("//*[@id='root']/div/main/div/div/p[2]");

    public AuthorizationPageObject(WebDriver driver) {
        super(driver);
    }

    public void fillingTheEmailField(String email) {
        driver.findElement(authorizationEmail).sendKeys(email);
    }

    public void fillingThePasswordField(String password) {
        driver.findElement(authorizationPassword).sendKeys(password, Keys.ENTER);
    }

    public void clickTheLinkToRegistrationPageFromAuthorizationPage() {
        driver.findElement(linkToRegistrationPageFromAuthorizationPage).click();
    }

    public void clickThePasswordRecoveryLink() {
        driver.findElement(passwordRecoveryLink).click();
    }

    public void waitNameTopicVisibility() {
        new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.visibilityOfElementLocated(nameTopic));
    }

    public String getTextFromTopicName() {
        WebElement wb = driver.findElement(nameTopic);
        return wb.getText();
    }

    public String getTextFromQuestionOnAuthorizationForm() {
        driver.get("https://stellarburgers.nomoreparties.site/login");
        driver.navigate().refresh();
        WebElement wb = driver.findElement(questionOnAuthorizationPage);
        return wb.getText();
    }
}

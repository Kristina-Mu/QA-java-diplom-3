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

    @Step("Заполняем поле 'Электронная почта'")
    public void fillingTheEmailField(String email) {
        driver.findElement(authorizationEmail).sendKeys(email);
    }

    @Step("Заполняем поле 'Пароль'")
    public void fillingThePasswordField(String password) {
        driver.findElement(authorizationPassword).sendKeys(password, Keys.ENTER);
    }

    @Step("Нажимаем на ссылку регистрации из формы авторизации")
    public void clickTheLinkToRegistrationPageFromAuthorizationPage() {
        driver.findElement(linkToRegistrationPageFromAuthorizationPage).click();
    }

    @Step("Нажимаем на ссылку восстановления пароля из формы авторизации")
    public void clickThePasswordRecoveryLink() {
        driver.findElement(passwordRecoveryLink).click();
    }

    @Step("Ждем, пока тема 'Имя' станет видимой")
    public void waitNameTopicVisibility() {
        new WebDriverWait(driver, Duration.ofSeconds(100))
                .until(ExpectedConditions.visibilityOfElementLocated(nameTopic));
    }

    @Step("Получаем текст из темы 'Имя'")
    public String getTextFromTopicName() {
        WebElement wb = driver.findElement(nameTopic);
        return wb.getText();
    }

    @Step("Получаем текст из вопроса на форме авторизации")
    public String getTextFromQuestionOnAuthorizationForm() {
        driver.get("https://stellarburgers.nomoreparties.site/login");
        driver.navigate().refresh();
        WebElement wb = driver.findElement(questionOnAuthorizationPage);
        return wb.getText();
    }
}

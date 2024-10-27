package praktikum;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RegistrationPageObject extends site.stellarburgers.pageobject.DriverClass {

    private final By nameField = By.xpath("//*[@id='root']/div/main/div/form/fieldset[1]/div/div/input");
    private final By emailField = By.xpath("//*[@id='root']/div/main/div/form/fieldset[2]/div/div/input");
    private final By passwordField = By.xpath("/html/body/div/div/main/div/form/fieldset[3]/div/div/input");
    private final By registrationButton = By.xpath("//*[@id='root']/div/main/div/form/button");
    private final By enterLinkInRegistrationForm = By.xpath("//*[@id='root']/div/main/div/div/p/a");
    private final By ErrorMessage = By.xpath("//*[@id='root']/div/main/div/form/fieldset[3]/div/p");

    public RegistrationPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Make click to the link Enter in personal area on the registration form")
    public void clickToLinkInRegistrationForm() {
        driver.findElement(enterLinkInRegistrationForm).click();
    }

    @Step("Add email in Email field")
    public void emailFieldFillingThroughRegistration(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    @Step("Add name in Name field")
    public void nameFieldFillingThroughRegistration(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    @Step("Add password in the password field")
    public void passwordFieldFillingThroughRegistration(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    @Step("Click the registration button to make registration")
    public void clickRegistrationButton() {

        driver.findElement(registrationButton).click();

    }

    @Step("Get error message when try to enter password less than 5 characters")
    public String getErrorMessageWhenIncorrectPassword() {
        return driver.findElement(ErrorMessage).getText();
    }

}




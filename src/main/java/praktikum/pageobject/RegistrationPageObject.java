package praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import praktikum.DriverClass;

public class RegistrationPageObject extends DriverClass {

    private final By nameField = By.xpath("//*[@id='root']/div/main/div/form/fieldset[1]/div/div/input");
    private final By emailField = By.xpath("//*[@id='root']/div/main/div/form/fieldset[2]/div/div/input");
    private final By passwordField = By.xpath("/html/body/div/div/main/div/form/fieldset[3]/div/div/input");
    private final By registrationButton = By.xpath("//*[@id='root']/div/main/div/form/button");
    private final By enterLinkInRegistrationForm = By.xpath("//*[@id='root']/div/main/div/div/p/a");
    private final By ErrorMessage = By.xpath("//*[@id='root']/div/main/div/form/fieldset[3]/div/p");

    public RegistrationPageObject(WebDriver driver) {
        super(driver);
    }

    public void clickToLinkInRegistrationForm() {
        driver.findElement(enterLinkInRegistrationForm).click();
    }

    public void emailFieldFillingThroughRegistration(String email) {
        driver.findElement(emailField).sendKeys(email);
    }

    public void nameFieldFillingThroughRegistration(String name) {
        driver.findElement(nameField).sendKeys(name);
    }

    public void passwordFieldFillingThroughRegistration(String password) {
        driver.findElement(passwordField).sendKeys(password);
    }

    public void clickRegistrationButton() {
        driver.findElement(registrationButton).click();
    }

    public String getErrorMessageWhenIncorrectPassword() {
        return driver.findElement(ErrorMessage).getText();
    }
}

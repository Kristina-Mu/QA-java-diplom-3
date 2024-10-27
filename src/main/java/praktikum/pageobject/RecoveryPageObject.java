package praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import praktikum.DriverClass;

public class RecoveryPageObject extends DriverClass {

    private final By enterButtonFromRecoveryPasswordPage = By.xpath("//*[@id='root']/div/main/div/div/p/a");

    public RecoveryPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Нажимаем на ссылку 'Войти' на странице восстановления пароля")
    public void clickEnterButtonFromRecoveryPasswordPage() {
        driver.findElement(enterButtonFromRecoveryPasswordPage).click();
    }
}

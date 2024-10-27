package praktikum.pageobject;



import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;

public class RecoveryPageObject extends site.stellarburgers.pageobject.DriverClass{

    private final By enterButtonFromRecoveryPasswordPage = By.xpath("//*[@id='root']/div/main/div/div/p/a");

    public RecoveryPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Make click to the link Recovery os password")
    public void clickEnterButtonFromRecoveryPasswordPage() {
        driver.findElement(enterButtonFromRecoveryPasswordPage).click();
    }

}
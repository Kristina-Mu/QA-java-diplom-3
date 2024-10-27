package praktikum;

import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class DriverClass {

    protected WebDriver driver;
    protected WebDriverWait wait;

    // Конструктор класса Page Object
    public DriverClass(WebDriver driver) {
        this.driver = driver; // Инициализируем поле driver
        this.wait = new WebDriverWait(driver, Duration.ofSeconds(200));
    }
}
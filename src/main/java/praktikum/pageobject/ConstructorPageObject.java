package praktikum.pageobject;

import io.qameta.allure.Step;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.DriverClass;

import java.time.Duration;

public class ConstructorPageObject extends DriverClass {
    private final By linkToConstructor = By.xpath("//*[@id='root']/div/header/nav/ul/li[1]/a/p");
    private final By constructorTopic = By.xpath("//*[@id='root']/div/main/section[1]/h1");
    private final By stellarBurgersLogoLink = By.xpath("//*[@id='root']/div/header/nav/div/a");
    private final By bunsTopic = By.xpath("//*[@id='root']/div/main/section[1]/div[1]/div[1]");
    private final By sousesTopic = By.xpath("//*[@id='root']/div/main/section[1]/div[1]/div[2]");
    private final By fillingsTopic = By.xpath("//*[@id='root']/div/main/section[1]/div[1]/div[3]");
    private final By fillingsTextName = By.xpath("//*[@id='root']/div/main/section[1]/div[2]/h2[3]");
    private final By bunsTextName = By.xpath("//*[@id='root']/div/main/section[1]/div[2]/h2[1]");

    public ConstructorPageObject(WebDriver driver) {
        super(driver);
    }

    @Step("Нажимаем на тему конструктора")
    public void clickToLinkToConstructor() {
        WebElement wb = driver.findElement(linkToConstructor);
        wb.click();
    }

    @Step("Получаем текст из темы конструктора")
    public String getTextFromConstructorTopic() {
        WebElement wb = driver.findElement(constructorTopic);
        return wb.getText();
    }

    @Step("Нажимаем на основной логотип")
    public void clickToStellarBurgersLogoLink() {
        WebElement wb = driver.findElement(stellarBurgersLogoLink);
        wb.click();
    }

    @Step("Нажимаем на булку в конструкторе и получаем класс для проверки активности")
    public String clickToBunMakeItActive() {
        WebElement wb = driver.findElement(bunsTopic);
        wb.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(bunsTextName));
        return wb.getAttribute("class");
    }

    @Step("Нажимаем на соусы в конструкторе и получаем класс для проверки активности")
    public String clickToSousesMakeItActive() {
        WebElement wb = driver.findElement(sousesTopic);
        wb.click();
        return wb.getAttribute("class");
    }

    @Step("Нажимаем на начинки в конструкторе и получаем класс для проверки активности")
    public String clickToFillingsMakeItActive() {
        WebElement wb = driver.findElement(fillingsTopic);
        wb.click();
        new WebDriverWait(driver, Duration.ofSeconds(10))
                .until(ExpectedConditions.visibilityOfElementLocated(fillingsTextName));
        return wb.getAttribute("class");
    }

    @Step("Ожидание изменения состояния класса")
    public String waitForClassToBecomeActive(By locator) {
        WebDriverWait wait = new WebDriverWait(driver, Duration.ofSeconds(10));
        WebElement element = wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
        return element.getAttribute("class");
    }

    @Step("Получаем локатор булок")
    public By getBunsTopicLocator() {
        return bunsTopic;
    }
}

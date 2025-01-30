package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class MainPage {
    public static String topOrderButton = "div[class*='Header_Nav'] [class*='Button']";
    public static String bottomOrderButton = "div[class*='Header_Nav'] [class*='Button']";
    private final WebDriver driver;
    private final By cookieButton = By.id("rcc-confirm-button");
    protected final By goButton = By.cssSelector(".Header_Button__28dPO");
    protected final By orderInputField = By.xpath(".//input[contains(@class,'Input_Input__1iN_Z')]");
    protected final By orderForm = By.className("Order_Form__17u6u");
    protected final By inputName = By.cssSelector("input[placeholder='* Имя']");
    protected final By inputSurname = By.cssSelector("input[placeholder='* Фамилия']");
    protected final By inputAddress = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']");
    protected final By inputMetro = By.cssSelector("input[placeholder='* Станция метро']");
    protected final By inputPhoneNumber = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");


    //protected final By lineId(String itemId) = By.id("accordion__heading-'" + itemId + "'");

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

/*    public StatusPage clickOnGo() {
        driver.findElement(goButton).click();
        return new StatusPage(driver);
    }*/
    /*public void clickOnOrder(String orderButton) {driver.findElement(orderButton).click();}*/

  /*  public void typeOrderId(String orderId) {
        driver.findElement(orderInputField).sendKeys(orderId);
    }*/

/*    public void clickOnStatus() {
        driver.findElement(statusButton).click();
    }*/


    public MainPage openMainPage() {
        driver.get(EnvConfig.BASE_URL);

        return this;
    }
    public MainPage clickOrderButton(String button){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(button)));
        driver.findElement(By.cssSelector(button)).click();
        return this;
    }

    public void acceptCookies() {
        waitForCookiesFloater();
        driver.findElement(cookieButton).click();
        waitForCookiesFloaterToDisappear();

    }

    private void waitForCookiesFloater() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(cookieButton));
    }

    private void waitForCookiesFloaterToDisappear() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(cookieButton));
    }

    public MainPage fillOrderForm(String name){
            waitOrderForm();
            fillName(name);
            return this;
    }
    private void waitOrderForm(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderForm));
    }
    private void fillName(String name){
        driver.findElement(inputName).sendKeys(name);

    }

}

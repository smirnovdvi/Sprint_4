package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;


public class MainPage {
    public static String topOrderButton = "div[class*='Header_Nav'] [class*='Button']";
    public static String bottomOrderButton = "div[class*=Home_RoadMap] button[class*=Button_Button]";
    private final WebDriver driver;
    private final By cookieButton = By.id("rcc-confirm-button");
/*    protected final By orderForm = By.className("Order_Form__17u6u");
    protected final By inputName = By.cssSelector("input[placeholder='* Имя']");
    protected final By inputSurname = By.cssSelector("input[placeholder='* Фамилия']");
    protected final By inputAddress = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']");
    protected final By inputMetro = By.cssSelector("input[placeholder='* Станция метро']");
    protected final By inputPhoneNumber = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");
    protected final By buttonNext = By.cssSelector("button[class*=Button_Middle]");*/


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public void openMainPage() {
        driver.get(EnvConfig.BASE_URL);

    }
    public void clickOrderButton(String button){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(button)));
        driver.findElement(By.cssSelector(button)).click();
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

/*    public void fillOrderForm(String name, String surname, String address, String metro, String phoneNumber){
            waitOrderForm();
            fillName(name);
            fillSurname(surname);
            fillAddress(address);
            fillMetro(metro);
            fillPhoneNumber(phoneNumber);

    }
    public void clickButtonNext(){
        driver.findElement(buttonNext).click();
    }
    private void fillMetro(String metro) {
        String metroStationSelector = ".//div[contains(@class, 'Order_Text') and text()='" + metro +"']";
        System.out.println(metro);
        driver.findElement(inputMetro).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(metroStationSelector)));
        driver.findElement(By.xpath(metroStationSelector)).click();
    }

    private void fillPhoneNumber(String phoneNumber) {
        driver.findElement(inputPhoneNumber).sendKeys(phoneNumber);
    }

    private void fillAddress(String address) {
        driver.findElement(inputAddress).sendKeys(address);
    }

    private void waitOrderForm(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderForm));
    }
    private void fillName(String name){
        driver.findElement(inputName).sendKeys(name);

    }
    private void fillSurname(String surname){
        driver.findElement(inputSurname).sendKeys(surname);

    }*/

}

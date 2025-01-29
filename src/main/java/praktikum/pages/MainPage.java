package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import praktikum.EnvConfig;

public class MainPage {
    private final WebDriver driver;

    protected final By goButton = By.cssSelector(".Header_Button__28dPO");
    protected final By orderInputField = By.xpath(".//input[contains(@class,'Input_Input__1iN_Z')]");
    protected final By statusButton = By.className("Header_Link__1TAG7");


    public MainPage(WebDriver driver) {
        this.driver = driver;
    }

    public StatusPage clickOnGo() {
        driver.findElement(goButton).click();
        return new StatusPage(driver);
    }

    public void typeOrderId(String orderId) {
        driver.findElement(orderInputField).sendKeys(orderId);
    }

    public void clickOnStatus() {
        driver.findElement(statusButton).click();
    }

    public void openMainPage() {
        driver.get(EnvConfig.BASE_URL);
    }
}

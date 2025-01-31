package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

public class OrderPage {
    private final WebDriver driver;
    protected final By orderForm = By.className("Order_Form__17u6u");
    protected final By inputName = By.cssSelector("input[placeholder='* Имя']");
    protected final By inputSurname = By.cssSelector("input[placeholder='* Фамилия']");
    protected final By inputAddress = By.cssSelector("input[placeholder='* Адрес: куда привезти заказ']");
    protected final By inputMetro = By.cssSelector("input[placeholder='* Станция метро']");
    protected final By inputPhoneNumber = By.cssSelector("input[placeholder='* Телефон: на него позвонит курьер']");
    protected final By buttonNext = By.cssSelector("button[class*=Button_Middle]");

    public OrderPage(WebDriver driver) {
        this.driver = driver;
    }
    //Заполняем поля формы заказа
    public void fillOrderForm(String name, String surname, String address, String metro, String phoneNumber){
        waitOrderForm();
        fillName(name);
        fillSurname(surname);
        fillAddress(address);
        fillMetro(metro);
        fillPhoneNumber(phoneNumber);
    }
    //Нажимаем кнопку "Далее"
    public void clickButtonNext(){
        driver.findElement(buttonNext).click();
    }
    //Нажимаем на поле выбора станции метро, ожидаем появления списка станций и выбираем нужную нам
    private void fillMetro(String metro) {
        String metroStationSelector = ".//div[contains(@class, 'Order_Text') and text()='" + metro +"']";
        driver.findElement(inputMetro).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.xpath(metroStationSelector)));
        driver.findElement(By.xpath(metroStationSelector)).click();
    }
    //Заполняем поле с номером телефона
    private void fillPhoneNumber(String phoneNumber) {
        driver.findElement(inputPhoneNumber).sendKeys(phoneNumber);
    }
    //Заполняем поле адреса
    private void fillAddress(String address) {
        driver.findElement(inputAddress).sendKeys(address);
    }
    //Ожидаем появления формы заказа
    private void waitOrderForm(){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(orderForm));
    }
    //Заполняем поле имя
    private void fillName(String name){
        driver.findElement(inputName).sendKeys(name);

    }
    //Заполняем поле фамилии
    private void fillSurname(String surname){
        driver.findElement(inputSurname).sendKeys(surname);
    }
}

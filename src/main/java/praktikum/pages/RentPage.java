package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.Keys;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

public class RentPage {
    private final WebDriver driver;
    protected final By rentForm = By.cssSelector("div[class*=Order_Content]");
    protected final By inputDate = By.cssSelector("input[placeholder='* Когда привезти самокат']");
    protected final By orderFilledDate = By.cssSelector("div[class*=Dropdown-placeholder]");
    protected final By period = By.cssSelector("div[class*=Dropdown-option]:first-child");
    protected final By collor = By.cssSelector("label[for=black]");
    protected final By inputComment = By.cssSelector("input[placeholder='Комментарий для курьера']");
    protected final By submitButton = By.xpath(".//button[contains(@class, 'Button_Middle') and text()='Заказать']");
    protected final By confirmButton = By.xpath(".//button[contains(@class, 'Button_Middle') and text()='Да']");
    protected final By checkOrder = By.xpath(".//div[contains(@class, 'Order_ModalHeader') and text()='Заказ оформлен']");

    public RentPage(WebDriver driver) {
        this.driver = driver;
    }
    //Заполняем форму аренды
    public void fillRentForm(){
        waitRentForm();
        dateSelection();
        selectRentalPeriod();
        selectColor();
        fillComment();
        clickSubmitOrder();
    }
    //Ожидаем модальное окно подтверждения заказа и нажимаем "Да"
    public void confirmOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(confirmButton));
        driver.findElement(confirmButton).click();
    }
    //Нажимаем кнопку "Заказать"
    private void clickSubmitOrder() {
        driver.findElement(submitButton).click();
    }
    //Заполняем поле комментария для курьера
    private void fillComment() {
        driver.findElement(inputComment).sendKeys("Жду с нетерпением");
    }
    //Выбираем цвет самоката
    private void selectColor() {
        driver.findElement(collor).click();
    }
    //Выбираем срок аренды самоката
    private void selectRentalPeriod() {
        driver.findElement(orderFilledDate).click();
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(period));
        driver.findElement(period).click();
    }
    //Заполняем поле даты доставки
    private void dateSelection() {
        driver.findElement(inputDate).sendKeys("01.05.2025" + Keys.ENTER);
    }
    //Ожидаем появления формы аренды
    private void waitRentForm() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(rentForm));
    }
    //Ожидаем модалку подтверждающую оформления заказа
    public void checkOrder() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(checkOrder));
    }
}

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

    public MainPage(WebDriver driver) {
        this.driver = driver;
    }
    //Запуск главной страницы приложения
    public void openMainPage() {
        driver.get(EnvConfig.BASE_URL);

    }
    //Ожидаем появления кнопки Заказать
    public void clickOrderButton(String button){
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.cssSelector(button)));
        driver.findElement(By.cssSelector(button)).click();
    }
    //Ожидаем модальное окно Cookies, принимаем их и ожидаем скрытия модального окна
    public void acceptCookies() {
        waitForCookiesFloater();
        driver.findElement(cookieButton).click();
        waitForCookiesFloaterToDisappear();

    }
    //Ожидаем модальное окно Cookies
    private void waitForCookiesFloater() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(cookieButton));
    }
    //Ожидаем что модальное окно Cookies исчезло
    private void waitForCookiesFloaterToDisappear() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.invisibilityOfElementLocated(cookieButton));
    }
}

package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

import static org.junit.Assert.assertEquals;

public class FaqPage {
    private final WebDriver driver;
    private final By cookieButton = By.id("rcc-confirm-button");
    private final String questionsIdPrefix = "accordion__heading-";
    private final String answerIdPrefix = "accordion__panel-";

    public FaqPage(WebDriver driver) {
        this.driver = driver;
    }

    public FaqPage open() {
        driver.get(EnvConfig.BASE_URL);

        return this;
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
    //Нажимаем на строку вопроса
    public FaqPage clickOnQuestion(String id) {
        driver.findElement(By.id(questionsIdPrefix + id)).click();

        return this;
    }
    //Ожидаем появления ответа
    public FaqPage waitForAnswer(String id) {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(By.id(answerIdPrefix + id)));

        return this;
    }
    //Проверяем что ответы перед началом теста не видны
    public FaqPage checkAnswerIsInvisible(String id) {
        assert !driver.findElement(By.id(answerIdPrefix + id)).isDisplayed();

        return this;
    }
    // Проверяем что полученный ответ соответствует ожидаемому тексту
    public void checkCorrectText(String id, String answerText){
        String text = driver.findElement(By.id(answerIdPrefix + id)).getText();
        assertEquals(text, answerText);
    }
}


package praktikum.pages;

import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import praktikum.EnvConfig;

import java.time.Duration;

import static org.junit.Assert.assertTrue;

public class StatusPage {
    private final WebDriver driver;

    protected final By notFoundImage = By.cssSelector("img[alt='Not found']");

    public StatusPage(WebDriver driver) {
        this.driver = driver;
    }

    public void checkNotFoundMessage() {
        new WebDriverWait(driver, Duration.ofSeconds(EnvConfig.DEFAULT_TIMEOUT))
                .until(ExpectedConditions.visibilityOfElementLocated(notFoundImage));

        assertTrue(driver.findElement(notFoundImage).isDisplayed());
    }
}

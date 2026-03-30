package framework.utils;

import framework.driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.time.Duration;

public class WaitUtils {

    private static final int DEFAULT_TIMEOUT = 15;

    private static AppiumDriver driver() {
        return DriverManager.getDriver();
    }

    private static WebDriverWait wait(int seconds) {
        return new WebDriverWait(driver(), Duration.ofSeconds(seconds));
    }

    public static WebElement waitForVisibility(By locator) {
        return wait(DEFAULT_TIMEOUT).until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    public static java.util.List<WebElement> waitForAllVisible(By locator, int seconds) {
        return wait(seconds).until(
                ExpectedConditions.visibilityOfAllElementsLocatedBy(locator)
        );
    }

    public static WebElement waitForVisibility(By locator, int seconds) {
        return wait(seconds).until(
                ExpectedConditions.visibilityOfElementLocated(locator)
        );
    }

    public static WebElement waitForPresence(By locator, int seconds) {
        return wait(seconds).until(
                ExpectedConditions.presenceOfElementLocated(locator)
        );
    }

    public static WebElement waitForClickability(By locator) {
        return wait(DEFAULT_TIMEOUT).until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }

    public static WebElement waitForClickability(By locator, int seconds) {
        return wait(seconds).until(
                ExpectedConditions.elementToBeClickable(locator)
        );
    }

    public static boolean waitForInvisibility(By locator, int seconds) {
        return wait(seconds).until(
                ExpectedConditions.invisibilityOfElementLocated(locator)
        );
    }

    public static boolean waitForText(By locator, String text, int seconds) {
        return wait(seconds).until(
                ExpectedConditions.textToBePresentInElementLocated(locator, text)
        );
    }

    public static boolean isVisible(By locator, int seconds) {
        try {
            waitForVisibility(locator, seconds);
            return true;
        } catch (Exception e) {
            return false;
        }
    }
}
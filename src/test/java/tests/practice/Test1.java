package tests.practice;

import io.appium.java_client.AppiumBy;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.android.options.UiAutomator2Options;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.testng.Assert;

import java.net.URI;
import java.time.Duration;

public class Test1 {

    private static AndroidDriver driver;
    private static WebDriverWait wait;

    public static void main(String[] args) {
        try{
            setup();
            onboarding();
            Assert.assertEquals(offer(), "CLAIM MY OFFER");

            System.out.print("passed!");
        } catch (Exception e){
            System.out.println("failed: " + e.getMessage());
        } finally{
            tearDown();
        }

    }

    private static void setup() throws Exception {
        UiAutomator2Options options = new UiAutomator2Options();
        options
                .setPlatformName("Android")
                .setDeviceName("RZ8R30XNHAV")
                .setApp("src/main/resources/base.apk")
                .setAppPackage("com.monefy.app.lite")
                .setAppActivity("com.monefy.activities.main.MainActivity_")
                .setAutomationName("UiAutomator2")
                .setNoReset(false)
                .setAutoGrantPermissions(true);

        driver = new AndroidDriver(
                URI.create("http://127.0.0.1:4723").toURL(),
                options
        );
        wait = new WebDriverWait(driver, Duration.ofSeconds(15));
    }

    private static void tearDown(){
        if (driver != null){
            driver.quit();
        }
    }

    private static WebElement waitUntilClickable(By locator) {
        return wait.until(ExpectedConditions.elementToBeClickable(locator));
    }

    private static WebElement waitUntilVisible(By locator){
        return wait.until(ExpectedConditions.visibilityOfElementLocated(locator));
    }

    private static void click(By locator) {
        waitUntilClickable(locator).click();
    }

    private static void onboarding() {
        By continueButton = By.id("com.monefy.app.lite:id/buttonContinue");

        click(continueButton);
        click(continueButton);
        click(continueButton);
        click(continueButton);
    }

    private static String offer(){
        By offerButton = By.id("com.monefy.app.lite:id/buttonPurchase");
        By buttonUiSelectorText = AppiumBy.androidUIAutomator(
                "new UiSelector().text(\"CLAIM MY OFFER\")"
        );
        By buttonXpath = AppiumBy.xpath("//android.widget.Button[@text='CLAIM MY OFFER']");
        return waitUntilVisible(offerButton).getText();
    }
}

package framework.pages;

import framework.driver.DriverManager;
import io.appium.java_client.AppiumDriver;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

/* Represents the parent page object that other screen classes inherit from.
 This is where shared page behavior, common element interactions, and driver-backed helper methods should be centralized.
 */
public class BasePage {

    /* Why protected here: it allows child page classes to use these shared helpers directly, while keeping them hidden
     from unrelated classes outside the page-object hierarchy
     */
    protected AppiumDriver driver;

    public BasePage() {
        this.driver = DriverManager.getDriver();
    }

    protected WebElement find(By locator) {
        return driver.findElement(locator);
    }

    protected void click(By locator) {
        find(locator).click();
    }

    protected void type(By locator, String text) {
        WebElement element = find(locator);
        element.clear();
        element.sendKeys(text);
    }

    protected String getText(By locator) {
        return find(locator).getText();
    }
}
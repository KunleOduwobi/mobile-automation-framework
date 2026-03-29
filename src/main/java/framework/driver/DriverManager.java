package framework.driver;

import io.appium.java_client.AppiumDriver;

public class DriverManager {
    private static final ThreadLocal<AppiumDriver> driverThreadLocal = new ThreadLocal<>();

    // Stores the driver instance for the current test thread.
    public static void setDriver(AppiumDriver driver) {
        driverThreadLocal.set(driver);
    }

    // Returns the driver bound to the current test thread.
    public static AppiumDriver getDriver() {
        return driverThreadLocal.get();
    }

    // Quits the current thread's driver and clears it from thread-local storage.
    public static void quitDriver() {
        AppiumDriver driver = driverThreadLocal.get();
        if (driver != null) {
            driver.quit();
            driverThreadLocal.remove();
        }
    }
}

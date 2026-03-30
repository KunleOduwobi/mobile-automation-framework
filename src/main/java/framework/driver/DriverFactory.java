package framework.driver;

import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Capabilities;
import org.openqa.selenium.remote.RemoteWebDriver;

import java.net.URL;

// Builds the correct Appium driver for the requested platform and registers it for the current test thread.
public class DriverFactory {

    // Creates a platform-specific driver session using generated capabilities and stores it in DriverManager.
    public static void initDriver(String platform, String deviceName) {
        try {
            URL appiumServerUrl = new URL(System.getProperty("appium.url", "http://127.0.0.1:4723"));
            var options = CapabilityFactory.getOptions(platform, deviceName);

            AppiumDriver driver;
            if (platform.equalsIgnoreCase("android")) {
                driver = new AndroidDriver(appiumServerUrl, (Capabilities) options);
            } else if (platform.equalsIgnoreCase("ios")) {
                driver = new IOSDriver(appiumServerUrl, (Capabilities) options);
            } else {
                throw new IllegalArgumentException("Unsupported platform: " + platform);
            }

            DriverManager.setDriver(driver);
        } catch (Exception e) {
            throw new RuntimeException("Failed to initialize driver", e);
        }
    }
}

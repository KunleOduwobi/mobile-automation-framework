package framework.driver;

import framework.config.ConfigManager;
import framework.config.DeviceConfig;
import io.appium.java_client.AppiumDriver;
import io.appium.java_client.android.AndroidDriver;
import io.appium.java_client.ios.IOSDriver;
import org.openqa.selenium.Capabilities;

import java.net.URL;

// Builds the correct Appium driver for the requested platform and registers it for the current test thread.
public class DriverFactory {

    // Creates a platform-specific driver session using generated capabilities and stores it in DriverManager.
    public static void initDriver(String platform, DeviceConfig deviceConfig) {
        try {
            URL appiumServerUrl = new URL(ConfigManager.getAppiumUrl());
            var options = CapabilityFactory.getOptions(platform, deviceConfig);

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

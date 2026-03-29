package framework.driver;

import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.time.Duration;

// Creates the platform-specific Appium options used to start Android and iOS driver sessions.
public class CapabilityFactory {

    // Returns the correct capabilities object for the requested platform and target device.
    public static Object getOptions(String platform, String deviceName) {
        if (platform.equalsIgnoreCase("android")) {
            return new UiAutomator2Options()
                    .setPlatformName("Android")
                    .setDeviceName(deviceName)
                    .setAutomationName("UiAutomator2")
                    .setApp(System.getProperty("app.path"))
                    .setNoReset(false)
                    .setNewCommandTimeout(Duration.ofSeconds(120));
        }

        if (platform.equalsIgnoreCase("ios")) {
            return new XCUITestOptions()
                    .setPlatformName("iOS")
                    .setDeviceName(deviceName)
                    .setAutomationName("XCUITest")
                    .setApp(System.getProperty("app.path"))
                    .setNoReset(false)
                    .setNewCommandTimeout(Duration.ofSeconds(120));
        }

        throw new IllegalArgumentException("Unsupported platform: " + platform);
    }
}

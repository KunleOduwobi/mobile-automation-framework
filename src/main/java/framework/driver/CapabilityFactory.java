package framework.driver;

import framework.config.ConfigManager;
import framework.config.DeviceConfig;
import io.appium.java_client.android.options.UiAutomator2Options;
import io.appium.java_client.ios.options.XCUITestOptions;

import java.nio.file.Paths;
import java.time.Duration;

// Creates the platform-specific Appium options used to start Android and iOS driver sessions.
public class CapabilityFactory {

    // Returns the correct capabilities object for the requested platform and target device.
    public static Object getOptions(String platform, DeviceConfig deviceConfig) {
        if (platform.equalsIgnoreCase("android")) {
            return new UiAutomator2Options()
                    .setPlatformName("Android")
                    .setDeviceName(deviceConfig.getDeviceName())
                    .setUdid(deviceConfig.getDeviceName())
                    .setAutomationName("UiAutomator2")
//                    .setApp(Paths.get(ConfigManager.getAppPath()).toAbsolutePath().toString())
                    .setAppPackage("com.monefy.app.lite")
                    .setAppActivity("com.monefy.activities.main.MainActivity_")
                    .setAutoGrantPermissions(true)
                    .setNoReset(false)
                    .setNewCommandTimeout(Duration.ofSeconds(120))
                    .amend("adbExecTimeout", 60000)
                    .amend("uiautomator2ServerLaunchTimeout", 60000)
                    .amend("uiautomator2ServerInstallTimeout", 60000)
                    .amend("androidInstallTimeout", 90000)
                    .amend("systemPort", deviceConfig.getSystemPort());
        }

        if (platform.equalsIgnoreCase("ios")) {
            return new XCUITestOptions()
                    .setPlatformName("iOS")
                    .setDeviceName(deviceConfig.getDeviceName())
                    .setAutomationName("XCUITest")
                    .setApp(Paths.get(ConfigManager.getAppPath()).toAbsolutePath().toString())
                    .setNoReset(false)
                    .setNewCommandTimeout(Duration.ofSeconds(120));
        }

        throw new IllegalArgumentException("Unsupported platform: " + platform);
    }
}

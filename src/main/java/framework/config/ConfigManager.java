package framework.config;

import java.io.IOException;
import java.io.InputStream;
import java.io.UncheckedIOException;
import java.util.Arrays;
import java.util.List;
import java.util.Properties;
import java.util.stream.Collectors;

public class ConfigManager {

    private static final String CONFIG_FILE = "config/config.properties";
    private static final Properties PROPERTIES = loadProperties();

    // Prevents instantiation because this class only exposes static config helpers.
    private ConfigManager() {
    }

    // Loads the framework properties file once from the test classpath.
    private static Properties loadProperties() {
        Properties properties = new Properties();

        try (InputStream inputStream = ConfigManager.class.getClassLoader().getResourceAsStream(CONFIG_FILE)) {
            if (inputStream == null) {
                throw new IllegalStateException("Config file not found on classpath: " + CONFIG_FILE);
            }

            properties.load(inputStream);
            return properties;
        } catch (IOException e) {
            throw new UncheckedIOException("Failed to load config file: " + CONFIG_FILE, e);
        }
    }

    // Returns a required property value, allowing JVM system properties to override file values.
    public static String getProperty(String key) {
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isBlank()) {
            return systemValue;
        }

        String propertyValue = PROPERTIES.getProperty(key);
        if (propertyValue == null || propertyValue.isBlank()) {
            throw new IllegalArgumentException("Missing config property: " + key);
        }

        return propertyValue.trim();
    }

    // Returns a property value with a fallback default when no override or file value exists.
    public static String getProperty(String key, String defaultValue) {
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isBlank()) {
            return systemValue;
        }

        String propertyValue = PROPERTIES.getProperty(key);
        return propertyValue == null || propertyValue.isBlank() ? defaultValue : propertyValue.trim();
    }

    // Reads a required property and converts it to an integer.
    public static int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }

    // Reads an integer property and uses the provided default when the key is absent.
    public static int getIntProperty(String key, int defaultValue) {
        return Integer.parseInt(getProperty(key, String.valueOf(defaultValue)));
    }

    // Reads a required property and converts it to a boolean.
    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }

    // Reads a boolean property and uses the provided default when the key is absent.
    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        return Boolean.parseBoolean(getProperty(key, String.valueOf(defaultValue)));
    }

    // Returns the configured target platform for the test run.
    public static String getPlatform() {
        return getProperty("platform");
    }

    // Returns the default device name for single-device execution.
    public static String getDeviceName() {
        return getProperty("deviceName");
    }

    // Parses the Android device matrix used for parallel execution across multiple devices.
    public static List<DeviceConfig> getAndroidDevices() {
        String configuredDevices = getProperty("android.devices", "");
        if (!configuredDevices.isBlank()) {
            return Arrays.stream(configuredDevices.split(","))
                    .map(String::trim)
                    .filter(entry -> !entry.isEmpty())
                    .map(ConfigManager::parseDeviceConfig)
                    .collect(Collectors.toList());
        }

        return List.of(new DeviceConfig(getDeviceName(), getIntProperty("android.systemPort", 8201)));
    }

    // Returns the configured application path passed to Appium.
    public static String getAppPath() {
        return getProperty("app.path");
    }

    // Returns the Appium server URL used when creating driver sessions.
    public static String getAppiumUrl() {
        return getProperty("appium.url");
    }

    // Returns the default implicit wait timeout in seconds.
    public static int getImplicitWait() {
        return getIntProperty("implicit.wait");
    }

    // Returns the default explicit wait timeout in seconds.
    public static int getExplicitWait() {
        return getIntProperty("explicit.wait");
    }

    // Converts one android.devices entry in deviceName:systemPort format into a DeviceConfig.
    private static DeviceConfig parseDeviceConfig(String configEntry) {
        String[] parts = configEntry.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException(
                    "Invalid android.devices entry. Expected deviceName:systemPort but got: " + configEntry);
        }

        return new DeviceConfig(parts[0].trim(), Integer.parseInt(parts[1].trim()));
    }
}

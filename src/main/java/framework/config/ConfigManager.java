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

    private ConfigManager() {
    }

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

    public static String getProperty(String key, String defaultValue) {
        String systemValue = System.getProperty(key);
        if (systemValue != null && !systemValue.isBlank()) {
            return systemValue;
        }

        String propertyValue = PROPERTIES.getProperty(key);
        return propertyValue == null || propertyValue.isBlank() ? defaultValue : propertyValue.trim();
    }

    public static int getIntProperty(String key) {
        return Integer.parseInt(getProperty(key));
    }

    public static int getIntProperty(String key, int defaultValue) {
        return Integer.parseInt(getProperty(key, String.valueOf(defaultValue)));
    }

    public static boolean getBooleanProperty(String key) {
        return Boolean.parseBoolean(getProperty(key));
    }

    public static boolean getBooleanProperty(String key, boolean defaultValue) {
        return Boolean.parseBoolean(getProperty(key, String.valueOf(defaultValue)));
    }

    public static String getPlatform() {
        return getProperty("platform");
    }

    public static String getDeviceName() {
        return getProperty("deviceName");
    }

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

    public static String getAppPath() {
        return getProperty("app.path");
    }

    public static String getAppiumUrl() {
        return getProperty("appium.url");
    }

    public static int getImplicitWait() {
        return getIntProperty("implicit.wait");
    }

    public static int getExplicitWait() {
        return getIntProperty("explicit.wait");
    }

//    parses android.devices entries in deviceName:systemPort format.
    private static DeviceConfig parseDeviceConfig(String configEntry) {
        String[] parts = configEntry.split(":");
        if (parts.length != 2) {
            throw new IllegalArgumentException(
                    "Invalid android.devices entry. Expected deviceName:systemPort but got: " + configEntry);
        }

        return new DeviceConfig(parts[0].trim(), Integer.parseInt(parts[1].trim()));
    }
}

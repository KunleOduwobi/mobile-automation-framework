package framework.config;

import java.util.Objects;

// Holds the device identifier and UiAutomator2 systemPort assigned to one test session.
public class DeviceConfig {

    private final String deviceName;
    private final int systemPort;

    // Creates an immutable device definition used when starting a platform session.
    public DeviceConfig(String deviceName, int systemPort) {
        this.deviceName = Objects.requireNonNull(deviceName, "deviceName must not be null");
        this.systemPort = systemPort;
    }

    // Returns the device name or UDID that Appium should target.
    public String getDeviceName() {
        return deviceName;
    }

    // Returns the UiAutomator2 systemPort reserved for this device session.
    public int getSystemPort() {
        return systemPort;
    }

    // Returns a compact identifier used in logs and lock keys.
    @Override
    public String toString() {
        return deviceName + ":" + systemPort;
    }
}

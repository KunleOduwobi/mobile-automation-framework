package framework.config;

import java.util.Objects;

//  holds the assigned device and port
public class DeviceConfig {

    private final String deviceName;
    private final int systemPort;

    public DeviceConfig(String deviceName, int systemPort) {
        this.deviceName = Objects.requireNonNull(deviceName, "deviceName must not be null");
        this.systemPort = systemPort;
    }

    public String getDeviceName() {
        return deviceName;
    }

    public int getSystemPort() {
        return systemPort;
    }

    @Override
    public String toString() {
        return deviceName + ":" + systemPort;
    }
}

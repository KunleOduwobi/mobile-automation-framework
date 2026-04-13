package tests.context;

import framework.config.DeviceConfig;

import java.net.URI;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

public class ScenarioContext {

    private static final ThreadLocal<DeviceConfig> CURRENT_DEVICE = new ThreadLocal<>();
    private static final ThreadLocal<Lock> CURRENT_LOCK = new ThreadLocal<>();
    private static final ConcurrentMap<String, Lock> DEVICE_LOCKS = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, DeviceConfig> SCENARIO_DEVICES = new ConcurrentHashMap<>();

    private ScenarioContext() {
    }

    public static String scenarioKey(URI uri, int line) {
        return uri + ":" + line;
    }

    public static void assignDevice(String scenarioId, DeviceConfig deviceConfig) {
        SCENARIO_DEVICES.put(scenarioId, deviceConfig);
    }

    public static DeviceConfig activateDevice(String scenarioId) {
        DeviceConfig deviceConfig = SCENARIO_DEVICES.get(scenarioId);
        if (deviceConfig == null) {
            throw new IllegalStateException("No device is assigned for scenario: " + scenarioId);
        }

        Lock lock = lockFor(deviceConfig);
        lock.lock();
        CURRENT_DEVICE.set(deviceConfig);
        CURRENT_LOCK.set(lock);
        return deviceConfig;
    }

    public static void setDevice(DeviceConfig deviceConfig) {
        CURRENT_DEVICE.set(deviceConfig);
    }

    public static DeviceConfig requireDevice() {
        DeviceConfig deviceConfig = CURRENT_DEVICE.get();
        if (deviceConfig == null) {
            throw new IllegalStateException("No device is assigned to the current scenario thread");
        }

        return deviceConfig;
    }

    public static Lock lockFor(DeviceConfig deviceConfig) {
        return DEVICE_LOCKS.computeIfAbsent(deviceConfig.toString(), ignored -> new ReentrantLock());
    }

    public static void clear() {
        CURRENT_DEVICE.remove();
        Lock lock = CURRENT_LOCK.get();
        if (lock != null) {
            lock.unlock();
            CURRENT_LOCK.remove();
        }
    }
}

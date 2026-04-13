package tests.context;

import framework.config.DeviceConfig;

import java.net.URI;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentMap;
import java.util.concurrent.locks.Lock;
import java.util.concurrent.locks.ReentrantLock;

// Stores per-scenario device assignments and per-thread runtime state for parallel execution.
public class ScenarioContext {

    private static final ThreadLocal<DeviceConfig> CURRENT_DEVICE = new ThreadLocal<>();
    private static final ThreadLocal<Lock> CURRENT_LOCK = new ThreadLocal<>();
    private static final ConcurrentMap<String, Lock> DEVICE_LOCKS = new ConcurrentHashMap<>();
    private static final ConcurrentMap<String, DeviceConfig> SCENARIO_DEVICES = new ConcurrentHashMap<>();

    // Prevents instantiation because scenario state is managed through static helpers.
    private ScenarioContext() {
    }

    // Builds a stable scenario key from the feature URI and scenario line number.
    public static String scenarioKey(URI uri, int line) {
        return uri + ":" + line;
    }

    // Registers which device configuration should be used when a scenario starts.
    public static void assignDevice(String scenarioId, DeviceConfig deviceConfig) {
        SCENARIO_DEVICES.put(scenarioId, deviceConfig);
    }

    // Activates the assigned device for the current thread and acquires that device's execution lock.
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

    // Stores the current device directly on the thread when assignment is already known.
    public static void setDevice(DeviceConfig deviceConfig) {
        CURRENT_DEVICE.set(deviceConfig);
    }

    // Returns the active device for the current scenario thread.
    public static DeviceConfig requireDevice() {
        DeviceConfig deviceConfig = CURRENT_DEVICE.get();
        if (deviceConfig == null) {
            throw new IllegalStateException("No device is assigned to the current scenario thread");
        }

        return deviceConfig;
    }

    // Returns a reusable lock for a specific device so two scenarios do not use it at the same time.
    public static Lock lockFor(DeviceConfig deviceConfig) {
        return DEVICE_LOCKS.computeIfAbsent(deviceConfig.toString(), ignored -> new ReentrantLock());
    }

    // Clears thread-local scenario state and releases the device lock after execution completes.
    public static void clear() {
        CURRENT_DEVICE.remove();
        Lock lock = CURRENT_LOCK.get();
        if (lock != null) {
            lock.unlock();
            CURRENT_LOCK.remove();
        }
    }
}

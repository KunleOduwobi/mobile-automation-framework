package tests.steps;

import framework.driver.DriverFactory;
import framework.driver.DriverManager;
import framework.utils.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        String platform = System.getProperty("platform", "android");
        String deviceName = System.getProperty("deviceName", "emulator-5554");

        DriverFactory.initDriver(platform, deviceName);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ScreenshotUtils.takeScreenshotAsBytes();
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        DriverManager.quitDriver();
    }
}
package tests.steps;

import framework.config.ConfigManager;
import framework.config.DeviceConfig;
import framework.driver.DriverFactory;
import framework.driver.DriverManager;
import framework.utils.ScreenshotUtils;
import io.cucumber.java.After;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import tests.context.ScenarioContext;

public class Hooks {

    @Before
    public void setUp(Scenario scenario) {
        String platform = ConfigManager.getPlatform();
        DeviceConfig deviceConfig = ScenarioContext.activateDevice(scenario.getName());

        DriverFactory.initDriver(platform, deviceConfig);
    }

    @After
    public void tearDown(Scenario scenario) {
        if (scenario.isFailed()) {
            byte[] screenshot = ScreenshotUtils.takeScreenshotAsBytes();
            scenario.attach(screenshot, "image/png", scenario.getName());
        }
        DriverManager.quitDriver();
//      makes the data provider shape back to what TestNG expects
        ScenarioContext.clear();
    }
}

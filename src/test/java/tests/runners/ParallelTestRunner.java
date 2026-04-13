package tests.runners;

import framework.config.ConfigManager;
import framework.config.DeviceConfig;
import framework.utils.RetryAnalyzer;
import io.cucumber.testng.AbstractTestNGCucumberTests;
import io.cucumber.testng.CucumberOptions;
import io.cucumber.testng.FeatureWrapper;
import io.cucumber.testng.PickleWrapper;
import org.testng.annotations.DataProvider;
import org.testng.annotations.Test;
import tests.context.ScenarioContext;

import java.util.List;

@CucumberOptions(
        features = "src/test/java/resources/features",
        glue = {"tests.steps"},
        plugin = {
                "pretty",
                "html:target/cucumber-report.html",
                "json:target/cucumber.json"
        },
        tags = "@smoke"
)
public class ParallelTestRunner extends AbstractTestNGCucumberTests {

    @Override
    @Test(dataProvider = "scenarios", retryAnalyzer = RetryAnalyzer.class)
    public void runScenario(PickleWrapper pickleWrapper, FeatureWrapper featureWrapper) {
        super.runScenario(pickleWrapper, featureWrapper);
    }

    @Override
    @DataProvider(parallel = true)
    public Object[][] scenarios() {
        Object[][] baseScenarios = super.scenarios();
        List<DeviceConfig> devices = ConfigManager.getAndroidDevices();

        for (int i = 0; i < baseScenarios.length; i++) {
            PickleWrapper pickleWrapper = (PickleWrapper) baseScenarios[i][0];
            String scenarioKey = ScenarioContext.scenarioKey(
                    pickleWrapper.getPickle().getUri(),
                    pickleWrapper.getPickle().getLine());
            ScenarioContext.assignDevice(scenarioKey, devices.get(i % devices.size()));
        }

        return baseScenarios;
    }
}

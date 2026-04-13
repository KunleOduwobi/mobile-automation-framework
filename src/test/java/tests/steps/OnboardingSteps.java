package tests.steps;

import framework.pages.PageProvider;
import framework.pages.android.OfferPageAndroid;
import tests.context.AppFlows;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class OnboardingSteps {

    private final String platform = System.getProperty("platform", "android");
    private final OfferPageAndroid offerPageAndroid = (OfferPageAndroid) PageProvider.getOfferPage(platform);
    private final AppFlows appFlows = new AppFlows();

    @Given("the user is on the onboarding screen")
    public void theUserIsOnTheOnboardingScreen() {
//        app is launched
    }

    @When("the user views the slides")
    public void theUserViewsTheSlides() {
        appFlows.goToOfferScreen();
    }

    @Then("the user views the offer page")
    public void theUserViewsTheOfferPage() {
        if (platform.equalsIgnoreCase("android")) {
            Assert.assertEquals(
                    offerPageAndroid.getPageTitle(),
                    "Claim your one-time welcome offer");
        }
    }


}

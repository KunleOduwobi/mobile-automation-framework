package tests.steps;

import framework.pages.PageProvider;
import framework.pages.android.DashboardPageAndroid;
import framework.pages.android.OfferPageAndroid;
import framework.pages.android.OnboardingPageAndroid;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class OnboardingSteps {

    private final String platform = System.getProperty("platform", "android");
    OnboardingPageAndroid onboardingPageAndroid = (OnboardingPageAndroid) PageProvider.getOnboardingPage(platform);
    OfferPageAndroid offerPageAndroid = (OfferPageAndroid) PageProvider.getOfferPage(platform);
    DashboardPageAndroid dashboardPageAndroid = (DashboardPageAndroid) PageProvider.getDashboardPage(platform);

    @Given("the user is on the onboarding screen")
    public void theUserIsOnTheOnboardingScreen() {
//        app is launched
    }

    @When("the user views the slides")
    public void theUserViewsTheSlides() {
        if (platform.equalsIgnoreCase("android")) {
            onboardingPageAndroid.onboarding();
        }
    }

    @Then("the user views the offer page")
    public void theUserViewsTheOfferPage() {
        if (platform.equalsIgnoreCase("android")) {
            Assert.assertEquals(
                    offerPageAndroid.getPageTitle(),
                    "Claim your one-time welcome offer");
        }
    }

    @When("the user closes the offer")
    public void theUserClosesTheOffer() {
        if (platform.equalsIgnoreCase("android")) {
            offerPageAndroid.clickCloseOfferButton();
        }
    }

    @Then("the dashboard is displayed")
    public void theDashboardIsDisplayed (){
        if (platform.equalsIgnoreCase("android")) {
            Assert.assertEquals(
                    dashboardPageAndroid.getPageTitle(),
                    "Monefy");
        }
    }
}

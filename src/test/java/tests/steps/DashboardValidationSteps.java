package tests.steps;

import framework.pages.PageProvider;
import framework.pages.android.DashboardPageAndroid;
import framework.pages.android.OfferPageAndroid;
import tests.context.AppFlows;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class DashboardValidationSteps {

    private final String platform = System.getProperty("platform", "android");
    private final OfferPageAndroid offerPageAndroid = (OfferPageAndroid) PageProvider.getOfferPage(platform);
    private final DashboardPageAndroid dashboardPageAndroid = (DashboardPageAndroid) PageProvider.getDashboardPage(platform);
    private final AppFlows appFlows = new AppFlows();

    @Given("the user is on the offer screen")
    public void theUserIsOnOfferScreen() {
        appFlows.goToOfferScreen();
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

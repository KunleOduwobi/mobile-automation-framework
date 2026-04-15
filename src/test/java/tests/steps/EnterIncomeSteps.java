package tests.steps;

import framework.pages.PageProvider;
import framework.pages.android.DashboardPageAndroid;
import framework.pages.android.NewIncomePageAndroid;
import io.cucumber.java.en.And;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;
import org.testng.Assert;

public class EnterIncomeSteps {

    private final String platform = System.getProperty("platform", "android");
    private final DashboardPageAndroid dashboardPageAndroid = (DashboardPageAndroid) PageProvider.getDashboardPage(platform);
    private final NewIncomePageAndroid newIncomePageAndroid = (NewIncomePageAndroid) PageProvider.getNewIncomePage(platform);

    @When("the user clicks the add income button")
    public void theUserClicksTheAddIncomeButton() {
        if (platform.equalsIgnoreCase("android")) {
            dashboardPageAndroid.clickAddIncome();
        }
    }

    @Then("the new income screen is displayed")
    public void theNewIncomeScreenIsDisplayed() {
        Assert.assertEquals(newIncomePageAndroid.getPageTitle(), "New income");
    }

    @When("the user enters an income amount of {string}")
    public void theUserEntersAnIncomeAmount(String incomeAmount) {
        if (platform.equalsIgnoreCase("android")) {
            newIncomePageAndroid.enterIncomeAmount(incomeAmount);
        }
    }

    @Then("the income amount of {string} is displayed")
    public void theIncomeAmountIsDisplayed(String incomeAmount) {
        if (platform.equalsIgnoreCase("android")) {
            Assert.assertEquals(
                    newIncomePageAndroid.getIncomeAmount("100"),
                    incomeAmount);
        }
    }

    @When("the user enters an income note of {string}")
    public void theUserEntersAnIncomeNote(String note) {
        if (platform.equalsIgnoreCase("android")) {
            newIncomePageAndroid.enterIncomeNote(note);
        }
    }

    @And("the user clicks choose category")
    public void theUserClicksChooseCategory() {
        if (platform.equalsIgnoreCase("android")) {
            newIncomePageAndroid.clickChooseCategory();
        }
    }

    @And("the user selects an income category of {string}")
    public void theUserSelectsAnIncomeCategory(String categoryName) {
        if (platform.equalsIgnoreCase("android")) {
            newIncomePageAndroid.selectIncomeCategory(categoryName);
        }
    }

    @Then("the dashboard balance is updated from {string} balance, with {string} amount type, of {string} currency and {string} amount")
    public void theBalanceIsUpdated(String currentDisplayedBalance, String amountType, String currency, String incomingAmount) {
        if (platform.equalsIgnoreCase("android")) {
            Assert.assertEquals(
                    dashboardPageAndroid.balanceAmountVerification(currentDisplayedBalance, amountType, currency, incomingAmount).contains(currency + incomingAmount),
                    true);
        }
    }
}

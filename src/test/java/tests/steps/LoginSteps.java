package tests.steps;

import framework.pages.PageProvider;
import framework.pages.android.LoginPageAndroid;
import framework.pages.ios.LoginPageIOS;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.When;

public class LoginSteps {

    private final String platform = System.getProperty("platform", "android");
    LoginPageAndroid loginPageAndroid = (LoginPageAndroid) PageProvider.getLoginPage(platform);

    @Given("the user is on the login screen")
    public void theUserIsOnTheLoginScreen() {
        // app already launched
    }

    @When("the user logs in with username {string} and password {string}")
    public void theUserLogsIn(String username, String password) {
        if (platform.equalsIgnoreCase("android")) {
            loginPageAndroid.login(username, password);
        } else {
            ((LoginPageIOS) PageProvider.getLoginPage(platform)).login(username, password);
        }
    }
}
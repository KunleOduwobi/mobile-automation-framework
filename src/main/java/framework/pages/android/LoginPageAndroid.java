package framework.pages.android;

import framework.pages.BasePage;
import org.openqa.selenium.By;

public class LoginPageAndroid extends BasePage {
    private final By username = By.id("com.demo:id/username");
    private final By password = By.id("com.demo:id/password");
    private final By loginButton = By.id("com.demo:id/login");

    public void login(String user, String pass) {
        type(username, user);
        type(password, pass);
        click(loginButton);
    }
}
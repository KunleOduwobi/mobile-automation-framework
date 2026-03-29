package framework.pages.ios;

import framework.pages.BasePage;
import org.openqa.selenium.By;

public class LoginPageIOS extends BasePage {
    private final By username = By.xpath("//XCUIElementTypeTextField[@name='username']");
    private final By password = By.xpath("//XCUIElementTypeSecureTextField[@name='password']");
    private final By loginButton = By.xpath("//XCUIElementTypeButton[@name='login']");

    public void login(String user, String pass) {
        type(username, user);
        type(password, pass);
        click(loginButton);
    }
}
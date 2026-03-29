package framework.pages;

import framework.pages.android.LoginPageAndroid;
import framework.pages.ios.LoginPageIOS;

// Returns the correct page object implementation for the platform the test is running on.
public class PageProvider {

    // Creates the platform-specific login page so tests can use the right screen object for Android or iOS.
    public static Object getLoginPage(String platform) {
        if (platform.equalsIgnoreCase("android")) {
            return new LoginPageAndroid();
        } else if (platform.equalsIgnoreCase("ios")) {
            return new LoginPageIOS();
        }
        throw new IllegalArgumentException("Unsupported platform: " + platform);
    }
}

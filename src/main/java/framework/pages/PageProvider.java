package framework.pages;

import framework.pages.android.DashboardPageAndroid;
import framework.pages.android.LoginPageAndroid;
import framework.pages.android.OfferPageAndroid;
import framework.pages.android.OnboardingPageAndroid;
import framework.pages.ios.LoginPageIOS;

// Returns the correct page object implementation for the platform the test is running on.
public class PageProvider {

    // Creates the platform-specific login page so tests can use the right screen object for Android or iOS.
    public static BasePage getLoginPage(String platform) {
        if (platform.equalsIgnoreCase("android")) {
            return new LoginPageAndroid();
        } else if (platform.equalsIgnoreCase("ios")) {
            return new LoginPageIOS();
        }
        throw new IllegalArgumentException("Unsupported platform: " + platform);
    }

    public static Object getOnboardingPage(String platform) {
        if (platform.equalsIgnoreCase("android")) {
            return new OnboardingPageAndroid();
        } else if (platform.equalsIgnoreCase("ios")) {

        }
        throw new IllegalArgumentException("Unsupported platform: " + platform);
    }

    public static Object getOfferPage(String platform) {
        if (platform.equalsIgnoreCase("android")) {
            return new OfferPageAndroid();
        } else if (platform.equalsIgnoreCase("ios")) {

        }
        throw new IllegalArgumentException("Unsupported platform: " + platform);
    }

    public static Object getDashboardPage(String platform) {
        if (platform.equalsIgnoreCase("android")) {
            return new DashboardPageAndroid();
        } else if (platform.equalsIgnoreCase("ios")) {

        }
        throw new IllegalArgumentException("Unsupported platform: " + platform);
    }
}

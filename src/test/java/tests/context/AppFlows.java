package tests.context;

import framework.pages.PageProvider;
import framework.pages.android.OfferPageAndroid;
import framework.pages.android.OnboardingPageAndroid;
import org.testng.Assert;

// Encapsulates reusable user flows that multiple step-definition classes can share.
public class AppFlows {

    private final String platform = System.getProperty("platform", "android");
    private final OnboardingPageAndroid onboardingPageAndroid =
            (OnboardingPageAndroid) PageProvider.getOnboardingPage(platform);
    private final OfferPageAndroid offerPageAndroid =
            (OfferPageAndroid) PageProvider.getOfferPage(platform);

    // Completes the onboarding journey and verifies that the offer screen is displayed at the end.
    public void goToOfferScreen() {
        if (platform.equalsIgnoreCase("android")) {
            Assert.assertTrue(
                    onboardingPageAndroid.isOnOnboardingScreen(),
                    "Expected app to open on the onboarding screen before continuing");
            onboardingPageAndroid.onboarding();
            Assert.assertEquals(
                    offerPageAndroid.getPageTitle(),
                    "Claim your one-time welcome offer");
        }
    }
}

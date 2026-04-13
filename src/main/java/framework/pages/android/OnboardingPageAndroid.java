package framework.pages.android;

import framework.pages.BasePage;
import org.openqa.selenium.By;

public class OnboardingPageAndroid extends BasePage {

    private final By continueButton = By.id("com.monefy.app.lite:id/buttonContinue");

    public boolean isOnOnboardingScreen() {
        return isDisplayed(continueButton);
    }

    public void onboarding() {
        for (int slide = 0; slide < 4; slide++) {
            click(continueButton);
        }
    }
}

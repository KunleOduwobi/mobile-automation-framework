package framework.pages.android;

import framework.pages.BasePage;
import org.openqa.selenium.By;

public class OnboardingPageAndroid extends BasePage {

    private final By continueButton = By.id("com.monefy.app.lite:id/buttonContinue");

    public void onboarding(){
        click(continueButton);
        click(continueButton);
        click(continueButton);
        click(continueButton);
    }

}

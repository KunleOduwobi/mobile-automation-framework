package framework.pages.android;

import framework.pages.BasePage;
import org.openqa.selenium.By;

public class OfferPageAndroid extends BasePage {

    private final By title = By.id("com.monefy.app.lite:id/textViewTitle");
    private final By closeButton = By.id("com.monefy.app.lite:id/buttonClose");

    public String getPageTitle(){
        return getText(title);
    }

}

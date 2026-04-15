package framework.pages.android;

import framework.pages.BasePage;
import org.openqa.selenium.By;

public class NewIncomePageAndroid extends BasePage {

    private final By pageTitle = By.xpath("//android.widget.TextView[@text=\"New income\"]");
    private final By incomeAmountField = By.id("com.monefy.app.lite:id/amount_text");
    private final By noteField = By.id("com.monefy.app.lite:id/textViewNote");
    private final By buttonKeyboard0 = By.id("com.monefy.app.lite:id/buttonKeyboard0");
    private final By buttonKeyboard1 = By.id("com.monefy.app.lite:id/buttonKeyboard1");
    private final By buttonKeyboard2 = By.id("com.monefy.app.lite:id/buttonKeyboard2");
    private final By buttonKeyboard3 = By.id("com.monefy.app.lite:id/buttonKeyboard3");
    private final By buttonKeyboard4 = By.id("com.monefy.app.lite:id/buttonKeyboard4");
    private final By buttonKeyboard5 = By.id("com.monefy.app.lite:id/buttonKeyboard5");
    private final By buttonKeyboard6 = By.id("com.monefy.app.lite:id/buttonKeyboard6");
    private final By buttonKeyboard7 = By.id("com.monefy.app.lite:id/buttonKeyboard7");
    private final By buttonKeyboard8 = By.id("com.monefy.app.lite:id/buttonKeyboard8");
    private final By buttonKeyboard9 = By.id("com.monefy.app.lite:id/buttonKeyboard9");
    private final By buttonKeyboardDot = By.id("com.monefy.app.lite:id/buttonKeyboardDot");
    private final By chooseCategoryButton = By.id("com.monefy.app.lite:id/keyboard_action_button");


    public String getPageTitle() {
        return getText(pageTitle);
    }

    public void enterIncomeAmount(String amount) {
        // Enter income amount using the custom keyboard
        for (char digit : amount.toCharArray()) {
            switch (digit) {
                case '0':
                    driver.findElement(buttonKeyboard0).click();
                    break;
                case '1':
                    driver.findElement(buttonKeyboard1).click();
                    break;
                case '2':
                    driver.findElement(buttonKeyboard2).click();
                    break;
                case '3':
                    driver.findElement(buttonKeyboard3).click();
                    break;
                case '4':
                    driver.findElement(buttonKeyboard4).click();
                    break;
                case '5':
                    driver.findElement(buttonKeyboard5).click();
                    break;
                case '6':
                    driver.findElement(buttonKeyboard6).click();
                    break;
                case '7':
                    driver.findElement(buttonKeyboard7).click();
                    break;
                case '8':
                    driver.findElement(buttonKeyboard8).click();
                    break;
                case '9':
                    driver.findElement(buttonKeyboard9).click();
                    break;
                case '.':
                    driver.findElement(buttonKeyboardDot).click();
                    break;
                default:
                    throw new IllegalArgumentException("Invalid character in amount: " + digit);
            }
        }
    }

    public String getIncomeAmount(String expectedAmount) {
        String actualAmount = getText(incomeAmountField);
        return actualAmount;
    }

    public void enterIncomeNote(String note) {
        type(noteField, note);
    }

    public void clickChooseCategory() {
        click(chooseCategoryButton);
    }

    public void selectIncomeCategory(String categoryName) {
        scrollToText(categoryName);
        click(By.xpath("//android.widget.TextView[@text='" + categoryName + "']"));
    }
}

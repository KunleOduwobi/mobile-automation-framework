package framework.pages.android;

import framework.pages.BasePage;
import org.openqa.selenium.By;

public class DashboardPageAndroid extends BasePage {

    private final By headerTitle = By.xpath("//android.widget.TextView[@text=\"Monefy\"]");
//    private final By currentMonth = By.xpath("//*[@resource-id='com.monefy.app.lite:id/pts_main']//android.widget.TextView");
//    private final By balanceAmount = By.id("com.monefy.app.lite:id/balance_amount");
//    private final By incomeAmount = By.id("com.monefy.app.lite:id/income_amount_text");
//    private final By expenseAmount = By.id("com.monefy.app.lite:id/expense_amount_text");
//    private final By addExpenseBtn = By.id("com.monefy.app.lite:id/expense_button");
//    private final By addIncomeBtn = By.id("com.monefy.app.lite:id/income_button");


    public String getPageTitle() {
        return getText(headerTitle);
    }

}

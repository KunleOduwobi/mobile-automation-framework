package framework.pages.android;

import framework.pages.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebElement;

import java.math.BigDecimal;
import java.math.RoundingMode;

public class DashboardPageAndroid extends BasePage {

    private final By headerTitle = By.xpath("//android.widget.TextView[@text=\"Monefy\"]");
    private final By currentMonth = By.xpath("//*[@resource-id='com.monefy.app.lite:id/pts_main']//android.widget.TextView");
    private final By balanceAmount = By.id("com.monefy.app.lite:id/balance_amount");
    private final By incomeAmount = By.id("com.monefy.app.lite:id/income_amount_text");
    private final By expenseAmount = By.id("com.monefy.app.lite:id/expense_amount_text");
    private final By addExpenseBtn = By.id("com.monefy.app.lite:id/expense_button");
    private final By addIncomeBtn = By.id("com.monefy.app.lite:id/income_button");


    public String getPageTitle() {
        return getText(headerTitle);
    }

    public String getCurrentMonth()
    {
        return getText(currentMonth);
    }

    public void clickAddIncome() {
        click(addIncomeBtn);
    }


    //	Verify Balance Amount
    public String balanceAmountVerification(String currentDisplayedBalance, String amountType, String currency, String incomingAmount) {

        System.out.println("Current Balance: " + currentDisplayedBalance);
        System.out.println("Incoming Amount: " + incomingAmount);
//		Calculate expected balance based on the type of amount being added (income or expense)
        BigDecimal currentBalance = new BigDecimal(currentDisplayedBalance);
        BigDecimal incomingAmountFormatted = new BigDecimal(incomingAmount.replaceAll(",", ""));
        BigDecimal expectedBalance;

        if (amountType.equalsIgnoreCase("income")) {
            expectedBalance = currentBalance.add(incomingAmountFormatted).setScale(2, RoundingMode.HALF_UP);
        } else if (amountType.equalsIgnoreCase("expense")) {
            expectedBalance = currentBalance.subtract(incomingAmountFormatted).setScale(2, RoundingMode.HALF_UP);
        } else {
            throw new IllegalArgumentException("Invalid AmountType. Must be 'income' or 'expense'.");
        }

//		If the expected balance is negative, format it with the negative sign before the currency symbol
        String expectedBalanceStr;
        if (expectedBalance.compareTo(BigDecimal.ZERO) < 0) {
            expectedBalanceStr = "-" + currency + expectedBalance.abs().toString();
        } else {
            expectedBalanceStr = currency + expectedBalance.toString();
        }
        return expectedBalanceStr;

    }
}

package com.zeroWebAppSecurity.pages;

import com.zeroWebAppSecurity.utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

import java.util.List;

public class PayBillsPage extends BasePage {

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div/div[2]/ul/li[3]/a")
    WebElement purchaseForeignCurrency;
    @FindBy(id = "pc_currency")
    WebElement drpCurrency;
    @FindBy(id = "pc_amount")
    WebElement amount;
    @FindBy(id = "pc_inDollars_true")
    WebElement radiobtnUSD;
    @FindBy(id = "pc_calculate_costs")
    WebElement calculateCostbtn;
    @FindBy(id = "pc_conversion_amount")
    WebElement conversionAmount;

    public PayBillsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage newInstance(WebDriver driver) {
        return new PayBillsPage(driver);
    }

    public void clickPurchaseForeignCurrency(){
        waitForElementToBeClickableAndClick(purchaseForeignCurrency);
    }

    public void selectCurrency(String currency){
        //drpCurrency.selectByVisibleText("Canada (dollar)");
        List<WebElement> ddlFromAccountsOptions = drpCurrency.findElements(By.tagName("option"));

        for (WebElement ddlFromAccountsOption : ddlFromAccountsOptions) {
            String optionText = ddlFromAccountsOption.getText();
            if (optionText.contains(currency)) {
                ddlFromAccountsOption.click();
                break;
            }
        }
    }

    public void setAmount(String moneyAmount){
        clearAndSendKeys(amount, moneyAmount);
    }

    public void checkRadioBtnForUSD(){
        waitForElementToBeClickableAndClick(radiobtnUSD);
    }

    public void clickCalculateCost(){
        waitForElementToBeClickableAndClick(calculateCostbtn);
        //return new PayBills(getDriver());
    }

    public void acceptAlert(){
        getDriver().switchTo().alert().accept();
    }

    public String getTextFromAlert() {
        String text = getDriver().switchTo().alert().getText();
        return text;
    }

    public String valueForAssertions(){
        String CanadaDollartoUSD = conversionAmount.getText();
        String CanadaDollar = CanadaDollartoUSD.substring(0,5);
        return CanadaDollar;
    }
}

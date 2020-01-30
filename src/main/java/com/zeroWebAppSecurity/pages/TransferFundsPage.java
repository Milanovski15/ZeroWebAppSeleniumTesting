package com.zeroWebAppSecurity.pages;

import com.zeroWebAppSecurity.utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;
import java.util.List;

public class TransferFundsPage extends BasePage {

    @FindBy(id = "tf_fromAccountId")
    WebElement drpFromAccount;
    @FindBy(id = "tf_toAccountId")
    WebElement drpToAccount;
    @FindBy(id = "tf_amount")
    WebElement amount;
    @FindBy(id = "tf_description")
    WebElement description;
    @FindBy(id = "btn_submit")
    WebElement btnContinue;
    @FindBy(id = "btn_submit")
    WebElement btnSubmit;
    @FindBy(id = "btn_cancel")
    WebElement btnCancel;
    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div/div[1]")
    WebElement succesfullySubmittedTransaction;
    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[1]/div[2]")
    WebElement fromAccount;
    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[2]/div[2]")
    WebElement toAccount;
    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[2]/div/div/div[2]/div/div[3]/div[2]")
    WebElement transferedAmount;

    public TransferFundsPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage newInstance(WebDriver driver) {
        return new TransferFundsPage(driver);
    }

    public void setDrpFromAccount(String fromAccount) {
        List<WebElement> ddlFromAccountsOptions = drpFromAccount.findElements(By.tagName("option"));
        for (WebElement ddlFromAccountsOption : ddlFromAccountsOptions) {
            String optionText = ddlFromAccountsOption.getText();
            if (optionText.contains(fromAccount)) {
                ddlFromAccountsOption.click();
                break;
            }
        }
    }

    public void setDrpToAccount(String fromAccount) {
        List<WebElement> ddlToAccountsOptions = drpToAccount.findElements(By.tagName("option"));
        for (WebElement ddlToAccountsOption : ddlToAccountsOptions) {
            String optionText = ddlToAccountsOption.getText();
            if (optionText.contains(fromAccount)) {
                ddlToAccountsOption.click();
                break;
            }
        }
    }

    public void setAmount(String moneyAmount){
        clearAndSendKeys(amount, moneyAmount);
    }

    public void setDescription(String desc){
        clearAndSendKeys(description, desc);
    }

    public void pressContinue(){
        waitForElementToBeClickableAndClick(btnContinue);
    }

    public void pressSubmit(){
        waitForElementToBeClickableAndClick(btnSubmit);
    }

    public void pressCancel(){
        waitForElementToBeClickableAndClick(btnCancel);
    }

    public String getSuccesfullySubmittedTransactionText(){
        return succesfullySubmittedTransaction.getText();
    }

    public String getFromAccountString(){
        return fromAccount.getText();
    }

    public String getToAccountString(){
        return toAccount.getText();
    }

    public String getTransferedAmountString(){
        return transferedAmount.getText();
    }
}

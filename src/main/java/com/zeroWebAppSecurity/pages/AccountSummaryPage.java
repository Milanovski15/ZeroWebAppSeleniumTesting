package com.zeroWebAppSecurity.pages;

import com.zeroWebAppSecurity.utils.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.support.FindBy;
import org.openqa.selenium.WebElement;

public class AccountSummaryPage extends BasePage {

    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[1]/div/div/ul/li[4]/a")
    WebElement payBills;
    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[1]/div/div/ul/li[3]/a")
    WebElement transferFunds;
    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[1]/div/div/ul/li[2]/a")
    WebElement accountActivity;
    @FindBy(xpath = "/html/body/div[1]/div[2]/div/div[1]/div/div/ul/li[6]/a")
    WebElement onlineStatements;

    public AccountSummaryPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage newInstance(WebDriver driver) {
        return new AccountSummaryPage(driver);
    }

    public PayBillsPage clickPayBills(){
        waitForElementToBeClickableAndClick(payBills);
        return new PayBillsPage(getDriver());
    }

    public TransferFundsPage clickTransferFunds(){
        waitForElementToBeClickableAndClick(transferFunds);
        return new TransferFundsPage(getDriver());
    }

    public AccountActivityPage clickAccountActivity(){
        waitForElementToBeClickableAndClick(accountActivity);
        return new AccountActivityPage(getDriver());
    }

}

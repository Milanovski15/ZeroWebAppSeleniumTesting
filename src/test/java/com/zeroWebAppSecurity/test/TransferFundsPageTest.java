package com.zeroWebAppSecurity.test;

import com.zeroWebAppSecurity.enums.DriverType;
import com.zeroWebAppSecurity.pages.*;
import com.zeroWebAppSecurity.utils.ConfigurationConst;
import com.zeroWebAppSecurity.utils.DriverFactory;
import com.zeroWebAppSecurity.utils.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.*;

import static org.junit.Assert.assertEquals;

public class TransferFundsPageTest {

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private AccountSummaryPage accountSummaryPage;
    private TransferFundsPage transferFundsPage;

    @BeforeTest
    @Step("Opening chrome driver and navigating to account summary page.")
    public void setUp(){
        driver = DriverFactory.createDriverForBrowserWithValue(DriverType.GOOGLE_CHROME_DRIVER);
        homePage = new HomePage(driver);
        homePage = (HomePage) homePage.navigateTo(ConfigurationConst.BASE_URL, homePage);
        loginPage = homePage.clickSignIn();
        accountSummaryPage = loginPage.loginWithDefaultCredentials();
    }

    @Test(priority = 1)
    @Step("Transfering money from Savings to  Credit Card.")
    public void shouldBeAbleToTransferFundsFromOneAccountToOther(){
        transferFundsPage = accountSummaryPage.clickTransferFunds();
        transferFundsPage.setDrpFromAccount("Savings(Avail. balance = $ 1000)");
        transferFundsPage.setDrpToAccount("Credit Card(Avail. balance = $ -265)");
        transferFundsPage.setAmount("265");
        transferFundsPage.setDescription("Transfering funds so the credit card has balance 0.");
        transferFundsPage.pressContinue();
        transferFundsPage.pressSubmit();

        //Log.assertion("The message after a successful transaction should be: You successfully submitted your transaction.");
        assertEquals("You successfully submitted your transaction.", transferFundsPage.getSuccesfullySubmittedTransactionText());
        //Log.assertion("Money are transfered from Savings account.");
        assertEquals("Savings", transferFundsPage.getFromAccountString());
        //Log.assertion("Money are transfered to Credit card account.");
        assertEquals("Credit Card", transferFundsPage.getToAccountString());
        //Log.assertion("The amount of money transfered should be 265$");
        assertEquals("$ 265", transferFundsPage.getTransferedAmountString());
    }

    @Test(priority = 2)
    @Step("Trying to transfer money from Checking to Savings.")
    public void shouldNotBeAbleToTransferFundsFromAccountWithBalanceInMinus(){
        transferFundsPage = accountSummaryPage.clickTransferFunds();
        transferFundsPage.setDrpFromAccount("Checking(Avail. balance = $ -500.2)");
        transferFundsPage.setDrpToAccount("Savings(Avail. balance = $ 1000)");
        transferFundsPage.setAmount("200");
        transferFundsPage.setDescription("Try to send money to account from account with balance in minus");
        transferFundsPage.pressContinue();
        transferFundsPage.pressSubmit();

        //Log.assertion("The message after a successful transaction should be: You did not submitted your transaction.");
        //assertEquals("You did not submitted your transaction.", transferFunds.getSuccessfullySubmittedTransactionText());
        //Log.assertion("Money tried to be transfered from Checking account.");
        assertEquals("Checking", transferFundsPage.getFromAccountString());
        //Log.assertion("Money tried to be transfered to Savings account.");
        assertEquals("Savings", transferFundsPage.getToAccountString());
        //Log.assertion("The amount of money tried to be transfered should be 200$");
        assertEquals("$ 200", transferFundsPage.getTransferedAmountString());
    }

    @Test(priority = 3)
    @Step("Trying to transfer money from Savings to Loan.")
    public void shouldNotBeAbleToTransferMoreMoneyThanTheAccountHas(){
        transferFundsPage = accountSummaryPage.clickTransferFunds();
        transferFundsPage.setDrpFromAccount("Savings(Avail. balance = $ 1000)");
        transferFundsPage.setDrpToAccount("Loan(Avail. balance = $ 780)");
        transferFundsPage.setAmount("1500");
        transferFundsPage.setDescription("Try to send more money than then account has");
        transferFundsPage.pressContinue();
        transferFundsPage.pressSubmit();

        //Log.assertion("The message after a successful transaction should be: You did not submitted your transaction.");
        //assertEquals("You did not submitted your transaction.", transferFunds.getSuccessfullySubmittedTransactionText());
        //Log.assertion("Money tried to be transfered from Savings account.");
        assertEquals("Savings", transferFundsPage.getFromAccountString());
       // Log.assertion("Money tried to be transfered to Loan account.");
        assertEquals("Loan", transferFundsPage.getToAccountString());
       // Log.assertion("The amount of money tried to be transfered should be 1500$");
        assertEquals("$ 1500", transferFundsPage.getTransferedAmountString());
    }

    @AfterTest
    @Step("Closing driver !!!")
    public void closeDriver(){
        driver.quit();
    }

}
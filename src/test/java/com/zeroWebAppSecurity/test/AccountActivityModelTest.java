package com.zeroWebAppSecurity.test;

import com.zeroWebAppSecurity.Data.DataProviders.AccountAcitivityDataProvider;
import com.zeroWebAppSecurity.enums.DriverType;
import com.zeroWebAppSecurity.models.AccountShowTransactionModel;
import com.zeroWebAppSecurity.pages.*;
import com.zeroWebAppSecurity.utils.ConfigurationConst;
import com.zeroWebAppSecurity.utils.DriverFactory;
import com.zeroWebAppSecurity.utils.Log;
import io.qameta.allure.Step;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.AfterTest;
import org.testng.annotations.BeforeTest;
import org.testng.annotations.Test;

import java.util.Optional;

import static org.testng.Assert.assertEquals;
import static org.testng.Assert.assertTrue;

public class AccountActivityModelTest {

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private AccountSummaryPage accountSummaryPage;
    private AccountActivityPage accountActivityPage;

    @BeforeTest
    @Step("Opening chrome driver and navigating to account activity page")
    public void setUp(){
        driver = DriverFactory.createDriverForBrowserWithValue(DriverType.GOOGLE_CHROME_DRIVER);
        homePage = new HomePage(driver);
        homePage = (HomePage) homePage.navigateTo(ConfigurationConst.BASE_URL, homePage);
        loginPage = homePage.clickSignIn();
        loginPage.sendCredentials();
        accountSummaryPage = loginPage.submitCredentials();
        accountActivityPage = accountSummaryPage.clickAccountActivity();
    }

    @Test(priority = 1, description = "Should be able to read the first row of the account activity table.")
    @Step("Getting and asserting the first row of the account activity table.")
    public void shouldBeAbleToReadFromAccountActivityTable(){
        //accountActivityPage.readTransactions();
        accountActivityPage = new AccountActivityPage(driver);
        accountActivityPage.readTransactions();
        Optional<AccountShowTransactionModel> actualAccountSavingOptional = accountActivityPage.findAccountByDateAndModel("2012-09-06");
        assertTrue(actualAccountSavingOptional.isPresent());
        AccountShowTransactionModel transactionModelDeposit = actualAccountSavingOptional.get();
        AccountShowTransactionModel actualDeposit = AccountShowTransactionModel.builder().date(transactionModelDeposit.getDate()).description(transactionModelDeposit.getDescription()).deposit(transactionModelDeposit.getDeposit()).withdrawal(transactionModelDeposit.getWithdrawal()).build();

        //Log.assertion("");
        assertEquals(transactionModelDeposit.getDate(),actualDeposit.getDate());
        //Log.assertion("");
        assertEquals(transactionModelDeposit.getDeposit(),actualDeposit.getDeposit());
        //Log.assertion("");
        assertEquals(transactionModelDeposit.getDescription(),actualDeposit.getDescription());
        //Log.assertion("");
        assertEquals(transactionModelDeposit.getWithdrawal(),actualDeposit.getWithdrawal());
    }

    @Test(dataProvider = "accountActivityDataProvider", dataProviderClass = AccountAcitivityDataProvider.class, priority = 2)
    public void shouldBeAbleToReadFromAccountActivityTableWithDataProvider(String date){
        accountActivityPage = new AccountActivityPage(driver);
        accountActivityPage.readTransactions();
        Optional<AccountShowTransactionModel> actualAccountSavingOptional = accountActivityPage.findAccountByDateAndModel(date);
        assertTrue(actualAccountSavingOptional.isPresent());
        AccountShowTransactionModel transactionModelDeposit = actualAccountSavingOptional.get();
        AccountShowTransactionModel actualDeposit = AccountShowTransactionModel.builder().date(transactionModelDeposit.getDate()).description(transactionModelDeposit.getDescription()).deposit(transactionModelDeposit.getDeposit()).withdrawal(transactionModelDeposit.getWithdrawal()).build();

        assertEquals(transactionModelDeposit.getDate(),actualDeposit.getDate());
        assertEquals(transactionModelDeposit.getDeposit(),actualDeposit.getDeposit());
        assertEquals(transactionModelDeposit.getDescription(),actualDeposit.getDescription());
        assertEquals(transactionModelDeposit.getWithdrawal(),actualDeposit.getWithdrawal());
    }

    @AfterTest
    @Step("Closing Driver !!!")
    public void closeDriver(){
        driver.quit();
    }

}
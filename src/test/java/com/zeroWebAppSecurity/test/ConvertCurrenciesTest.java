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

public class ConvertCurrenciesTest {

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private AccountSummaryPage accountSummaryPage;
    private PayBillsPage payBillsPage;

    @BeforeTest
    @Step("Opening chrome driver and navigating to pay bills page.")
    public void setUp(){
        driver = DriverFactory.createDriverForBrowserWithValue(DriverType.GOOGLE_CHROME_DRIVER);
        homePage = new HomePage(driver);
        homePage = (HomePage) homePage.navigateTo(ConfigurationConst.BASE_URL, homePage);
        loginPage = homePage.clickSignIn();
        accountSummaryPage = loginPage.loginWithDefaultCredentials();
        payBillsPage = accountSummaryPage.clickPayBills();
        payBillsPage.clickPurchaseForeignCurrency();
    }

    @Test(priority = 3)
    @Step("Selecting Canadian dollar to convert to USD.")
    public void shouldConvertCanadianDollarToUSDollar() {
        payBillsPage.selectCurrency("Canada (dollar)");
        payBillsPage.setAmount("100");
        payBillsPage.checkRadioBtnForUSD();
        payBillsPage.clickCalculateCost();
        String valueForAssertion = payBillsPage.valueForAssertions();

        //Log.assertion("The assertion value (100 USD to Canadian Dollar) should be 94.19");
        assertEquals("94.19", valueForAssertion);
    }

    @Test(priority = 2)
    @Step("Trying to convert without selecting currency.")
    public void shouldNotBeAbleToConvertIfCurrencyIfNotSelected(){
        payBillsPage.setAmount("100");
        payBillsPage.checkRadioBtnForUSD();
        payBillsPage.clickCalculateCost();
        payBillsPage.implicitWait(10);
        String textForAssertion = payBillsPage.getTextFromAlert();
        payBillsPage.acceptAlert();

        //Log.assertion("The text from the alert message should be: Please, ensure that you have filled all the required fields with valid values.");
        assertEquals("Please, ensure that you have filled all the required fields with valid values.", textForAssertion);
    }

    @Test(priority = 1)
    @Step("Trying to convert without entering amount.")
    public void shouldNotBeAbleToConvertCurrencyIfThereIsNoAmount(){
        payBillsPage.selectCurrency("Canada (dollar)");
        payBillsPage.checkRadioBtnForUSD();
        payBillsPage.clickCalculateCost();
        payBillsPage.implicitWait(10);
        String textForAssertion = payBillsPage.getTextFromAlert();
        payBillsPage.acceptAlert();

       // Log.assertion("The text from the alert message should be: Please, ensure that you have filled all the required fields with valid values.");
        assertEquals("Please, ensure that you have filled all the required fields with valid values.", textForAssertion);
    }

    @AfterTest
    @Step("Closing driver !!!")
    public void closeDriver(){
        driver.quit();
    }
}
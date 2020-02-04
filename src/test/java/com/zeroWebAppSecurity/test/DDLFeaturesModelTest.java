package com.zeroWebAppSecurity.test;

import com.zeroWebAppSecurity.enums.DriverType;
import com.zeroWebAppSecurity.enums.DropDownEnum;
import com.zeroWebAppSecurity.models.TestAssertModel;
import com.zeroWebAppSecurity.pages.AccountSummaryPage;
import com.zeroWebAppSecurity.pages.HomePage;
import com.zeroWebAppSecurity.pages.LoginPage;
import com.zeroWebAppSecurity.pages.TransferFundsPage;
import com.zeroWebAppSecurity.utils.ConfigurationConst;
import com.zeroWebAppSecurity.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.Assert;
import org.testng.annotations.*;

import java.util.ArrayList;
import java.util.List;

public class DDLFeaturesModelTest {

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;
    private AccountSummaryPage accountSummaryPage;
    private TransferFundsPage transferFundsPage;
    List<String> list = new ArrayList<>();

    @BeforeTest
    public void login(){
        driver = DriverFactory.createDriverForBrowserWithValue(DriverType.GOOGLE_CHROME_DRIVER);
        homePage = new HomePage(driver);
        homePage = (HomePage) homePage.navigateTo(ConfigurationConst.BASE_URL, homePage);
        loginPage = homePage.clickSignIn();
        accountSummaryPage = loginPage.loginWithDefaultCredentials();
        transferFundsPage = accountSummaryPage.clickTransferFunds();
        list.add("Savings(Avail. balance = $ 1000)");
        list.add("Checking(Avail. balance = $ -500.2)");
    }

    @Test(priority = 0)
    public void checkIfRightValuesArePresentedInFromAccountDDL() {
        boolean itContains = transferFundsPage.checkFromAcc(list);
        Assert.assertTrue(itContains == true);
    }

    @Test(priority = 0)
    public void checkIfRightValuesArePresentedInToAccountDDL() {
        boolean itContains = transferFundsPage.checkToAcc(list);
        Assert.assertTrue(itContains == true);
    }

    @AfterTest
    public void closeDriver(){
        driver.quit();
    }

}

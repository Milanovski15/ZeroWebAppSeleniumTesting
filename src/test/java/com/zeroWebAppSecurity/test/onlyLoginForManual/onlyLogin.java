package com.zeroWebAppSecurity.test.onlyLoginForManual;

import com.zeroWebAppSecurity.enums.DriverType;
import com.zeroWebAppSecurity.pages.HomePage;
import com.zeroWebAppSecurity.pages.LoginPage;
import com.zeroWebAppSecurity.utils.ConfigurationConst;
import com.zeroWebAppSecurity.utils.DriverFactory;
import org.openqa.selenium.WebDriver;
import org.testng.annotations.Test;

public class onlyLogin {

    private WebDriver driver;
    private HomePage homePage;
    private LoginPage loginPage;

    @Test
    public void login(){
        driver = DriverFactory.createDriverForBrowserWithValue(DriverType.GOOGLE_CHROME_DRIVER);
        homePage = new HomePage(driver);
        homePage = (HomePage) homePage.navigateTo(ConfigurationConst.BASE_URL, homePage);
        loginPage = homePage.clickSignIn();
        loginPage.loginWithDefaultCredentials();
    }
}
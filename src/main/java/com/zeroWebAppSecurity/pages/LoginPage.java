package com.zeroWebAppSecurity.pages;

import com.zeroWebAppSecurity.utils.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class LoginPage extends BasePage {

    public static final String USERNAME_DEFAULT = "username";
    public static final String PASSWORD_DEFAULT = "password";

    @FindBy(id = "user_login")
    WebElement userName;
    @FindBy(id = "user_password")
    WebElement password;
    @FindBy(name = "submit")
    WebElement submitbtn;

    public LoginPage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage newInstance(WebDriver driver) {
        return new LoginPage(driver);
    }

    public AccountSummaryPage loginWithDefaultCredentials(){
        clearAndSendKeys(userName, USERNAME_DEFAULT);
        clearAndSendKeys(password, PASSWORD_DEFAULT);
        waitForElementToBeClickableAndClick(submitbtn);
        return new AccountSummaryPage(getDriver());
    }



}

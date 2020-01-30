package com.zeroWebAppSecurity.pages;

import com.zeroWebAppSecurity.utils.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.support.FindBy;

public class HomePage extends BasePage {

    @FindBy (id = "signin_button")
    WebElement signIn;

    public HomePage(WebDriver driver) {
        super(driver);
    }

    @Override
    public BasePage newInstance(WebDriver driver) {
        return new HomePage(driver);
    }

    public LoginPage clickSignIn(){
        moveToElement(signIn);
        waitForElementToBeClickableAndClick(signIn);
        return new LoginPage(this.getDriver());
}
}

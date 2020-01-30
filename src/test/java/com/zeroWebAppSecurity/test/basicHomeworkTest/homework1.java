package com.zeroWebAppSecurity.test.basicHomeworkTest;

import org.junit.Test;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;
import org.openqa.selenium.support.ui.Select;

import java.util.List;
import java.util.concurrent.TimeUnit;

import static org.junit.Assert.assertEquals;


public class homework1 {

    public static void main(String[] args) {
        System.setProperty("webdriver.gecko.driver", "C:\\gecko driver\\geckodriver.exe");
        WebDriver driver = new FirefoxDriver();
        //driver.get("http://zero.webappsecurity.com/");
        driver.navigate().to("http://zero.webappsecurity.com/index.html");
        driver.manage().timeouts().pageLoadTimeout(20, TimeUnit.SECONDS);
        Actions actions = new Actions(driver);
/*--------------------*/
        WebDriverWait wait = new WebDriverWait(driver, 10);
/*------------------------*/
        wait.until(ExpectedConditions.visibilityOfAllElementsLocatedBy(By.id("signin_button")));
        WebElement btnSignIn = driver.findElement(By.id("signin_button"));
        actions.moveToElement(btnSignIn).perform();
        btnSignIn.click();
/*--------------------*/
        WebElement userName = driver.findElement(By.id("user_login"));
        userName.sendKeys("username");
        WebElement password = driver.findElement(By.id("user_password"));
        password.sendKeys("password");

        WebElement submit = driver.findElement(By.name("submit"));
        submit.click();
        /*----------------------------*/
        WebElement payBills = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[1]/div/div/ul/li[4]/a"));
        payBills.click();
/*----------------------------------------------------------------*/
        WebElement purchaseForeignCurrency = driver.findElement(By.xpath("/html/body/div[1]/div[2]/div/div[2]/div/div/div[2]/ul/li[3]/a"));
        purchaseForeignCurrency.click();

/*------------------------------------*/
        Select drpCurrency = new Select(driver.findElement(By.id("pc_currency")));
        drpCurrency.selectByVisibleText("Canada (dollar)");
/*---------------------------*/
        WebElement amount = driver.findElement(By.id("pc_amount"));
        amount.sendKeys("100");
/*-------------------------------------------------*/
        WebElement radiobtnUSD = driver.findElement(By.id("pc_inDollars_true"));
        radiobtnUSD.click();
/*------------------------------------------*/
        WebElement calculateCostbtn = driver.findElement(By.id("pc_calculate_costs"));
        calculateCostbtn.click();
/*-----------------------------------------------*/
        WebElement conversionAmount = driver.findElement(By.id("pc_conversion_amount"));
/*-----------------------*/
        String USD = amount.getAttribute("value");

        String CanadaDollartoUSD = conversionAmount.getText();
        String CanadaDollar = CanadaDollartoUSD.substring(0,5);

       // assertEquals("94.19",CanadaDollar);

        //driver.close();
    }


}

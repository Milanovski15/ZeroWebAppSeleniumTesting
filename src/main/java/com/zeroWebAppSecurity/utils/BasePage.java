package com.zeroWebAppSecurity.utils;


import org.openqa.selenium.By;
import org.openqa.selenium.JavascriptExecutor;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;
import org.openqa.selenium.interactions.Actions;
import org.openqa.selenium.support.PageFactory;
import org.openqa.selenium.support.pagefactory.AjaxElementLocatorFactory;
import org.openqa.selenium.support.ui.ExpectedConditions;
import org.openqa.selenium.support.ui.WebDriverWait;

import java.util.List;
import java.util.Optional;
import java.util.concurrent.TimeUnit;

public abstract class BasePage {

    private WebDriver driver;
    private WebDriverWait wait;
    private AjaxElementLocatorFactory factory;
    private Actions actions;
    private JavascriptExecutor javascriptExecutor;
    public abstract BasePage newInstance(WebDriver driver);

    public BasePage(WebDriver driver) {
        this.driver = driver;
        this.factory = new AjaxElementLocatorFactory(driver, ConfigurationConst.MAX_RETRY_FOR_LOCATING_ELEMENT_AJAX_FACTORY);
        PageFactory.initElements(factory, this);
        wait = new WebDriverWait(driver, ConfigurationConst.MAX_RETRY_FOR_LOCATING_ELEMENT);
        actions = new Actions(driver);
        javascriptExecutor = (JavascriptExecutor) driver;
    }

    protected void moveToElement(WebElement element) {
        actions.moveToElement(element);
        actions.perform();
    }

    public <T extends BasePage> BasePage navigateTo(String url, T type) {
        driver.get(url);
        return type.newInstance(driver);
    }

    protected void clearAndSendKeys(WebElement element, String text) {
        wait.until(ExpectedConditions.visibilityOf(element));
        moveToElement(element);
        element.clear();
        element.sendKeys(text);
    }

    protected void waitForElementToBeClickableAndClick(WebElement element) {
        moveToElement(element);
        wait.until(ExpectedConditions.elementToBeClickable(element));
        element.click();
    }

    protected WebElement waitAndFindElement(WebElement root, By locatorType) {
        wait.until(ExpectedConditions.presenceOfNestedElementLocatedBy(root, locatorType));
        return root.findElement(locatorType);
    }

    protected WebElement waitAndFindElementFromRoot(By locatorType) {
        wait.until(ExpectedConditions.presenceOfElementLocated(locatorType));
        return driver.findElement(locatorType);
    }

    protected List<WebElement> waitAndFindElements(WebElement root, By locatorType) {
        wait.until(ExpectedConditions.visibilityOfNestedElementsLocatedBy(root, locatorType));
        return root.findElements(locatorType);
    }

    protected Optional<WebElement> checkAndGetIfElementIsPresent(WebElement root, By locator) {
        try {
            return Optional.of(root.findElement(locator));
        } catch (Exception e) {
            return Optional.empty();
        }
    }

    public void implicitWait(int sec){
        driver.manage().timeouts().implicitlyWait(sec, TimeUnit.SECONDS);
    }

    protected  WebDriver getDriver(){
        return driver;
    };
}

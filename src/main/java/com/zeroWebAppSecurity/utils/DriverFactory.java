package com.zeroWebAppSecurity.utils;

import com.zeroWebAppSecurity.enums.DriverType;
import io.github.bonigarcia.wdm.ChromeDriverManager;
import io.github.bonigarcia.wdm.FirefoxDriverManager;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.chrome.ChromeDriver;
import org.openqa.selenium.firefox.FirefoxDriver;


public class DriverFactory {
    private final static String GOOGLE_CHROME_SET_UP_NAME = "webdriver.chrome.driver";
    private final static String GOOGLE_CHROME_SET_UP_PATH = "C:/myProject/zeroWebAppSecuritySelenium/src/main/resources/chromedriver.exe";

    private final static String FIREFOX_SET_UP_NAME = "webdriver.gecko.driver";
    private final static String FIREFOX_SET_UP_PATH = "C:/myProject/zeroWebAppSecuritySelenium/src/main/resources/geckodriver.exe";

    public static WebDriver createDriverForBrowserWithValue(DriverType driverType) {

        WebDriver driver = null;
        if (driverType.equals(DriverType.GOOGLE_CHROME_DRIVER)) {
            ChromeDriverManager.getInstance().version("2.40").setup();
            driver = new ChromeDriver();
        }
        if (driverType.equals(DriverType.FIRE_FOX_DRIVER)) {
            FirefoxDriverManager.getInstance().setup();
            driver = new FirefoxDriver();
        }
        if (driver == null) {
            throw new RuntimeException("The driver wasn't initialised");
        }
        driver.manage().window().maximize();
        return driver;
    }
}
package com.zeroWebAppSecurity.features.dropDown;

import com.zeroWebAppSecurity.utils.BasePage;
import org.openqa.selenium.By;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;

public class SimpleDropDown extends DropDownService {

    public static final String DDL_OPTION_TAG_LOCATOR = "option";

    @Override
    public BasePage newInstance(WebDriver driver) {
        return null;
    }

    public SimpleDropDown(WebDriver driver) {
        super(driver);
    }

    @Override
    protected String getTextFromOption(WebElement option) {
        return option.getText();
    }

    @Override
    protected WebElement getElementToClickFromOption(WebElement option) {
        return option;
    }

    @Override
    protected List<WebElement> findOptionRowsFromDDL(WebElement ddl) {
        return waitAndFindElements(ddl, By.tagName(DDL_OPTION_TAG_LOCATOR));
    }
}
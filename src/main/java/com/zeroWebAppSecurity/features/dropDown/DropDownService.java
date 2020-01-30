package com.zeroWebAppSecurity.features.dropDown;

import com.zeroWebAppSecurity.utils.BasePage;
import org.openqa.selenium.WebDriver;
import org.openqa.selenium.WebElement;

import java.util.List;
import java.util.stream.Collectors;

public abstract class DropDownService extends BasePage implements DropDownFeature {

    public DropDownService(WebDriver driver) {
        super(driver);
    }

    @Override
    public void chooseFromDDL(WebElement ddl, Enum<?> optionEnum) {
        waitForElementToBeClickableAndClick(ddl);
        List<WebElement> ddlOptions = findOptionRowsFromDDL(ddl);
        WebElement selectedOption = ddlOptions.stream()
                .filter(option -> getTextFromOption(option).contains(optionEnum.toString()))
                .findFirst()
                .orElseThrow(RuntimeException::new);
        WebElement elementToClickFromOption = getElementToClickFromOption(selectedOption);
        waitForElementToBeClickableAndClick(elementToClickFromOption);
        waitForElementToBeClickableAndClick(ddl);
    }

    @Override
    public List<String> getAvailableOptionsFromDDL(WebElement ddl) {
        waitForElementToBeClickableAndClick(ddl);
        List<WebElement> ddlOptions = findOptionRowsFromDDL(ddl);
        List<String> optionsTextList = ddlOptions.stream()
                .map(this::getTextFromOption)
                .collect(Collectors.toList());
        waitForElementToBeClickableAndClick(ddl);
        return optionsTextList;
    }

    protected abstract String getTextFromOption(WebElement option);

    protected abstract WebElement getElementToClickFromOption(WebElement option);

    protected abstract List<WebElement> findOptionRowsFromDDL(WebElement ddl);

}

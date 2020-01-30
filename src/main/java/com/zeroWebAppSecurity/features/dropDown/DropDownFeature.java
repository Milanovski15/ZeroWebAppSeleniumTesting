package com.zeroWebAppSecurity.features.dropDown;

import org.openqa.selenium.WebElement;

import java.util.List;

public interface DropDownFeature {

    void chooseFromDDL(WebElement ddl, Enum<?> optionEnum);

    List<String> getAvailableOptionsFromDDL(WebElement ddl);

}

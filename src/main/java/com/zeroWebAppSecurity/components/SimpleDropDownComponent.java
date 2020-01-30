package com.zeroWebAppSecurity.components;

import com.zeroWebAppSecurity.features.dropDown.DropDownFeature;
import com.zeroWebAppSecurity.models.TestAssertModel;
import org.openqa.selenium.WebElement;

import java.util.Arrays;
import java.util.List;
import java.util.stream.Collectors;

public class SimpleDropDownComponent {
    private WebElement ddl;
    private DropDownFeature dropDownFeature;

    private SimpleDropDownComponent() {
    }

    public SimpleDropDownComponent(WebElement ddl, DropDownFeature dropDownFeature) {
        this.ddl = ddl;
        this.dropDownFeature = dropDownFeature;
    }

    public void chooseFromDDL(Enum<?> optionEnum) {
        dropDownFeature.chooseFromDDL(ddl, optionEnum);
    }

    public TestAssertModel checkDDlContainsValues(String... optionsTexts) {
        List<String> availableOptionsFromDDL = dropDownFeature.getAvailableOptionsFromDDL(ddl);
        List<String> modifiedItems = availableOptionsFromDDL.stream().
                map(s -> s.split("\\(")[0].trim())
                .collect(Collectors.toList());
        return TestAssertModel.builder()
                .passed(modifiedItems.containsAll(Arrays.asList(optionsTexts)))
                .errorMessage("Some of the expected values are missing in the drop down list")
                .build();
    }
}

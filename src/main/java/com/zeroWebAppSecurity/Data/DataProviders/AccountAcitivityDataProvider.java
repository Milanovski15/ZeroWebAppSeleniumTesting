package com.zeroWebAppSecurity.Data.DataProviders;

import org.testng.annotations.DataProvider;

public class AccountAcitivityDataProvider {
    @DataProvider(name = "accountActivityDateDataProvider")
    public static Object[][] date() {
        return new Object[][]{
                {"2012-09-06"},
                {"2012-09-05"},
                {"2012-09-01"},
        };
    }
}

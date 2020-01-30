package com.zeroWebAppSecurity.models;

import lombok.Builder;
import lombok.Data;

@Builder
@Data
public class TestAssertModel {

    private boolean passed;
    private String errorMessage;

}

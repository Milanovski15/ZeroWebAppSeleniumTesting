package com.zeroWebAppSecurity.models;

import lombok.*;

@Getter
@Builder
@AllArgsConstructor
@NoArgsConstructor
@Setter
public class AccountShowTransactionModel {

    private String date;
    private String description;
    private String deposit;
    private String withdrawal;

}

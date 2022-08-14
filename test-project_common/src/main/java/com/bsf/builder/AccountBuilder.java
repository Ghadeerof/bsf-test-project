package com.bsf.builder;

import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Builder
public class AccountBuilder {
    private String accountId;
    private String fullName;
    private String emailAddress;
    private BigDecimal accountBalance;
    private Date createdAt;
}

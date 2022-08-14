package com.bsf.dto;

import lombok.Getter;
import lombok.Setter;
import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class Account {

    private String accountId;
    private String fullName;
    private String emailAddress;
    private BigDecimal accountBalance;
    private Date createdAt;
}

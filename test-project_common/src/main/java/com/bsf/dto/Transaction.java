package com.bsf.dto;

import com.bsf.enums.TransactionStatus;
import lombok.Getter;
import lombok.Setter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Setter
public class Transaction {

    private String transactionId;
    private String senderAccountId;
    private String receiverAccountId;
    private BigDecimal transactionAmount;
    private TransactionStatus transactionStatus;
    private String reason;
    private Date createdAt;
}

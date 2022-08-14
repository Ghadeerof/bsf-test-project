package com.bsf.builder;

import com.bsf.enums.TransactionStatus;
import lombok.Builder;
import lombok.Getter;

import java.math.BigDecimal;
import java.util.Date;

@Getter
@Builder
public class TransactionBuilder {
    private String transactionId;
    private String senderAccountId;
    private String receiverAccountId;
    private BigDecimal transactionAmount;
    private TransactionStatus transactionStatus;
    private String reason;
    private Date createdAt;
}

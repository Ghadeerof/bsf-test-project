package com.bsf.entity;

import com.bsf.enums.TransactionStatus;
import com.bsf.utils.CustomSequenceGenerator;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;

import javax.persistence.*;
import javax.validation.constraints.NotNull;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_transaction")
public class TransactionEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "transaction_sequence")
    @GenericGenerator(name = "transaction_sequence", strategy = "com.bsf.utils.CustomSequenceGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = CustomSequenceGenerator.INCREMENT_PARAM, value = "1"),
            @org.hibernate.annotations.Parameter(name = CustomSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "TRAN"),
            @org.hibernate.annotations.Parameter(name = CustomSequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%d")})
    @Column(name = "transaction_id")
    private String transactionId;

    @NotNull(message = "Sender Account id must be present")
    @Column(name = "sender_id",nullable = false)
    private String senderAccountId;

    @NotNull(message = "Receiver Account id must be present")
    @Column(name = "receiver_id", nullable = false)
    private String receiverAccountId;

    @NotNull(message = "Transaction Amount cannot be absent.")
    @Column(name = "transaction_amount", nullable = false)
    private BigDecimal transactionAmount;

    @Column(name ="transaction_status", nullable = false)
    @Enumerated(EnumType.STRING)
    private TransactionStatus transactionStatus;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "reason")
    private String reason;

}

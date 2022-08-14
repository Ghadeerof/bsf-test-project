package com.bsf.entity;

import com.bsf.enums.AccountStatus;
import com.bsf.utils.CommonUtils;
import lombok.*;
import org.hibernate.annotations.GenericGenerator;
import com.bsf.utils.CustomSequenceGenerator;

import javax.persistence.*;
import javax.validation.constraints.Email;
import javax.validation.constraints.NotBlank;
import java.math.BigDecimal;
import java.util.Date;

@Entity
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@ToString
@Table(name = "tbl_account")
public class AccountEntity {

    @Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "account_sequence")
    @GenericGenerator(name = "account_sequence", strategy = "com.bsf.utils.CustomSequenceGenerator", parameters = {
            @org.hibernate.annotations.Parameter(name = CustomSequenceGenerator.INCREMENT_PARAM, value = "1"),
            @org.hibernate.annotations.Parameter(name = CustomSequenceGenerator.VALUE_PREFIX_PARAMETER, value = "ACC"),
            @org.hibernate.annotations.Parameter(name = CustomSequenceGenerator.NUMBER_FORMAT_PARAMETER, value = "%d")})
    @Column(name = "account_id")
    private String accountId;

    @Column(name = "full_name")
    @NotBlank(message = "Full name should not be blank")
    private String fullName;

    @Column(name = "email_address")
    @Email(message = "Email should not be blank",
            regexp = CommonUtils.EMAIL_PATTERN)
    private String emailAddress;

    @Column(name = "account_balance")
    private BigDecimal accountBalance;

    @Column(name = "status")
    @Enumerated(EnumType.STRING)
    private AccountStatus status = AccountStatus.INACTIVE;

    @Column(name = "created_at")
    private Date createdAt;

    @Column(name = "updated_at")
    private Date updatedAt;

    @Column(name = "deleted_at")
    private Date deletedAt;

    @PrePersist
    void before() {
        if (null == this.createdAt) {
            this.createdAt = new Date();

        }
    }
}

package com.bsf.mapper;

import com.bsf.builder.AccountBuilder;
import com.bsf.dto.Account;
import com.bsf.entity.AccountEntity;

import java.util.Date;

public class AccountMapper {

    private AccountMapper(){

    }

    public static AccountBuilder mapToAccountBuilder(AccountEntity accountEntity) {
        return AccountBuilder.builder()
                .accountId(accountEntity.getAccountId())
                .fullName(accountEntity.getFullName())
                .emailAddress(accountEntity.getEmailAddress())
                .accountBalance(accountEntity.getAccountBalance())
                .createdAt(accountEntity.getCreatedAt())
                .build();
    }

    public static AccountEntity mapToAccountEntity(Account account){
        AccountEntity entity = new AccountEntity();

        entity.setAccountId(account.getAccountId());
        entity.setFullName(account.getFullName());
        entity.setEmailAddress(account.getEmailAddress());
        entity.setAccountBalance(account.getAccountBalance());

        return entity;
    }

    public static AccountEntity mapToUpdateAccount(Account account, AccountEntity accountEntity) {
        if(account.getFullName() != null && account.getFullName() != accountEntity.getFullName()){
            accountEntity.setFullName(account.getFullName());
        }

        if(account.getEmailAddress() != null && account.getEmailAddress() != accountEntity.getEmailAddress()){
            accountEntity.setEmailAddress(account.getEmailAddress());
        }

        if(account.getAccountBalance() != null && account.getAccountBalance() != accountEntity.getAccountBalance()){
            accountEntity.setAccountBalance(account.getAccountBalance());
        }
        accountEntity.setUpdatedAt(new Date());

        return accountEntity;
    }

    public static Account mapToAccountDto(AccountEntity accountEntity){
        Account account = new Account();

        account.setAccountId(accountEntity.getAccountId());
        account.setFullName(accountEntity.getFullName());
        account.setEmailAddress(accountEntity.getEmailAddress());
        account.setAccountBalance(accountEntity.getAccountBalance());
        account.setCreatedAt(accountEntity.getCreatedAt());

        return account;
    }
}

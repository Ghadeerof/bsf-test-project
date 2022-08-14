package com.bsf.repository;

import com.bsf.entity.AccountEntity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Modifying;
import org.springframework.data.jpa.repository.Query;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

public interface AccountRepository extends JpaRepository<AccountEntity, String> {

    @Transactional
    @Modifying
    @Query("UPDATE AccountEntity a set a.accountBalance = a.accountBalance - ?2 where a.accountId = ?1")
    void debitAccount(String accountId, BigDecimal amount);

    @Transactional
    @Modifying
    @Query("UPDATE AccountEntity a set a.accountBalance = a.accountBalance + ?2 where a.accountId = ?1")
    void creditAccount(String accountId, BigDecimal amount);

}

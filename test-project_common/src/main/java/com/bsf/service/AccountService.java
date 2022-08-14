package com.bsf.service;

import com.bsf.dto.Account;
import com.bsf.dto.RequestWrapperDTO;
import com.bsf.exception.AccountCheckedException;

public interface AccountService {

    RequestWrapperDTO getAllAccounts();

    RequestWrapperDTO getAccountById(String accountId);

    RequestWrapperDTO addAccount(Account account);

    RequestWrapperDTO updateAccount(Account account) throws AccountCheckedException;

    RequestWrapperDTO deleteAccountById(String accountId) throws AccountCheckedException;
}

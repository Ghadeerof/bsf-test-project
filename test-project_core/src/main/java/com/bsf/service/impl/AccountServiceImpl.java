package com.bsf.service.impl;

import com.bsf.constant.AccountConstant;
import com.bsf.constant.MasterConstant;
import com.bsf.dto.Account;
import com.bsf.dto.RequestWrapperDTO;
import com.bsf.exception.AccountCheckedException;
import com.bsf.exception.AccountRecordNotFoundException;
import com.bsf.exception.AccountUncheckedException;
import com.bsf.entity.AccountEntity;
import com.bsf.enums.AccountError;
import com.bsf.enums.AccountStatus;
import com.bsf.enums.Domain;
import com.bsf.enums.SequencePrefix;
import com.bsf.mapper.AccountMapper;
import com.bsf.repository.AccountRepository;
import com.bsf.service.AccountService;
import com.bsf.utils.CommonUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class AccountServiceImpl implements AccountService {

    final private AccountRepository accountRepository;

    public AccountServiceImpl(AccountRepository accountRepository){
        this.accountRepository = accountRepository;
    }

    @Override
    public RequestWrapperDTO getAllAccounts() {
        List<AccountEntity> accounts = accountRepository.findAll();
        if (CollectionUtils.isNotEmpty(accounts)) {
            return CommonUtils.mapToWrapper(null, CommonUtils.buildForObjectMapper(AccountConstant.ACCOUNT_FIELD,
                    accounts.stream().map(AccountMapper::mapToAccountBuilder).collect(Collectors.toList())));
        } else{
            throw new AccountRecordNotFoundException();
        }
    }

    @Override
    public RequestWrapperDTO getAccountById(String accountId) {

        if(StringUtils.isBlank(accountId) && !isAccountIdValid(accountId)){
            throw new AccountUncheckedException(AccountError.ERROR_INVALID_REQ.getDescription(),
                    AccountError.ERROR_INVALID_REQ.getCode());
        }
        Optional<AccountEntity> accountEntity = accountRepository.findById(accountId);

        if (!accountEntity.isPresent() && ObjectUtils.isEmpty(accountEntity)){
            throw new AccountRecordNotFoundException();
        }

        return CommonUtils.mapToWrapper(CommonUtils.buildForObjectMapper(AccountConstant.ACCOUNT_ID, accountId),
                CommonUtils.buildForObjectMapper(Domain.ACCOUNT.getDescription(),
                        Stream.of(AccountMapper.mapToAccountBuilder(accountEntity.get())).collect(Collectors.toList())));

    }

    @Override
    public RequestWrapperDTO addAccount(Account account) {

        if (!isAccountValid(account)){
            throw new AccountUncheckedException(AccountError.ERROR_INVALID_REQ.getDescription()
                    , AccountError.ERROR_INVALID_REQ.getCode());
        }
        try{
            AccountEntity addedEntity = accountRepository.save(AccountMapper.mapToAccountEntity(account));

            if(ObjectUtils.isEmpty(addedEntity)){
                throw new AccountUncheckedException(String.format(AccountError.ERROR_CREATE.getDescription()
                        , Domain.ACCOUNT.getDescription()), AccountError.ERROR_CREATE.getCode());
            }

            return CommonUtils.mapToWrapper(account, CommonUtils.
                    buildForObjectMapper(AccountConstant.ACCOUNT_FIELD, AccountMapper.mapToAccountBuilder(addedEntity)));

        }catch (AccountUncheckedException ex){
            throw new AccountUncheckedException(ex.getMessage(), ex.getCode());
        }

    }

    @Override
    public RequestWrapperDTO updateAccount(Account account) throws AccountCheckedException{
        if (!isAccountValid(account)){
            throw new AccountUncheckedException(AccountError.ERROR_INVALID_REQ.getDescription()
                    , AccountError.ERROR_INVALID_REQ.getCode());
        }
        if(StringUtils.isBlank(account.getAccountId()) || !isAccountIdValid(account.getAccountId())){
            throw new AccountRecordNotFoundException();
        }

        try{
            Optional<AccountEntity> accountEntity = accountRepository.findById(account.getAccountId());
            if (!accountEntity.isPresent() && ObjectUtils.isEmpty(accountEntity)){
                throw new AccountRecordNotFoundException();
            }

            AccountEntity updatedEntity = accountRepository.save(AccountMapper.mapToUpdateAccount(account,accountEntity.get()));

            if(ObjectUtils.isEmpty(updatedEntity)){
                throw new AccountUncheckedException(String.format(AccountError.ERROR_UPDATE.getDescription()
                        , Domain.ACCOUNT.getDescription()), AccountError.ERROR_UPDATE.getCode());
            }
        }catch (AccountUncheckedException ex){
            throw new AccountUncheckedException(ex.getMessage(), ex.getCode());
        }catch (Exception ex){
            throw new AccountCheckedException(ex.getMessage(), ex, AccountError.ERROR_UPDATE.getCode());
        }

        return CommonUtils.mapToWrapper(account, Stream.of(CommonUtils.buildForObjectMapper(MasterConstant.MSG_FIELD,
                String.format(MasterConstant.UPDATE_SUCCESS, Domain.ACCOUNT.getDescription()))).collect(Collectors.toList()));
    }

    @Override
    public RequestWrapperDTO deleteAccountById(String accountId) throws AccountCheckedException{

        if(StringUtils.isBlank(accountId) || !isAccountIdValid(accountId)){
            throw new AccountUncheckedException(AccountError.ERROR_INVALID_REQ.getDescription()
                    , AccountError.ERROR_INVALID_REQ.getCode());
        }
        Optional<AccountEntity> entity = accountRepository.findById(accountId);
        if (!entity.isPresent()) {
            throw new AccountRecordNotFoundException();
        }

        AccountEntity deletedAccount;
        try {
            entity.get().setStatus(AccountStatus.INACTIVE);

            deletedAccount = accountRepository.save(entity.get());
        } catch (Exception e) {
            throw new AccountCheckedException(e.getMessage(), e, AccountError.ERROR_DELETE.getCode());
        }

        if(ObjectUtils.isEmpty(deletedAccount)){
            throw new AccountUncheckedException(
                    String.format(AccountError.ERROR_DELETE.getDescription(), Domain.ACCOUNT.getDescription()),
                    AccountError.ERROR_DELETE.getCode());
        }

        return CommonUtils.mapToWrapper((CommonUtils.buildForObjectMapper
                (AccountConstant.ACCOUNT_ID, accountId)), Stream.of(
                        CommonUtils.buildForObjectMapper(MasterConstant.MSG_FIELD,
                                String.format(MasterConstant.DELETE_SUCCESS, Domain.ACCOUNT.getDescription())))
                .collect(Collectors.toList()));
    }

    private boolean isAccountIdValid(String accountId) {
        return StringUtils.contains(accountId, SequencePrefix.ACC.toString());
    }

    private boolean isAccountValid(Account account){
        return ObjectUtils.isNotEmpty(account)
                && StringUtils.isNotBlank(account.getFullName())
                && StringUtils.isNotBlank(account.getEmailAddress());
    }
}

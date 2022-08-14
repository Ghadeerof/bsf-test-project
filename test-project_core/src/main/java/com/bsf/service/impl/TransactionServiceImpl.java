package com.bsf.service.impl;

import com.bsf.constant.TransactionConstant;
import com.bsf.dto.RequestWrapperDTO;
import com.bsf.dto.Transaction;
import com.bsf.exception.AccountRecordNotFoundException;
import com.bsf.exception.NotEnoughTransactionBalance;
import com.bsf.exception.TransactionRecordNotFoundException;
import com.bsf.exception.TransactionUncheckedException;
import com.bsf.entity.AccountEntity;
import com.bsf.entity.TransactionEntity;
import com.bsf.enums.Domain;
import com.bsf.enums.SequencePrefix;
import com.bsf.enums.TransactionError;
import com.bsf.mapper.TransactionMapper;
import com.bsf.repository.AccountRepository;
import com.bsf.repository.TransactionRepository;
import com.bsf.service.TransactionService;
import com.bsf.utils.CommonUtils;
import org.apache.commons.collections4.CollectionUtils;
import org.apache.commons.lang3.ObjectUtils;
import org.apache.commons.lang3.StringUtils;
import org.springframework.scheduling.annotation.Async;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.concurrent.CompletableFuture;
import java.util.stream.Collectors;
import java.util.stream.Stream;

@Service
public class TransactionServiceImpl implements TransactionService {

    private final TransactionRepository transactionRepository;

    private final AccountRepository accountRepository;

    public TransactionServiceImpl(TransactionRepository transactionRepository, AccountRepository accountRepository){
        this.transactionRepository = transactionRepository;
        this.accountRepository = accountRepository;
    }

    @Override
    public RequestWrapperDTO getAllTransactions() {
        List<TransactionEntity> transactions = transactionRepository.findAll();
        if (CollectionUtils.isNotEmpty(transactions)) {
            return CommonUtils.mapToWrapper(null, CommonUtils.buildForObjectMapper(TransactionConstant.TRANSACTION_FIELD,
                    transactions.stream().map(TransactionMapper::mapToTransactionBuilder).collect(Collectors.toList())));
        } else{
            throw new TransactionRecordNotFoundException();
        }
    }

    @Override
    public RequestWrapperDTO getTransactionById(String transactionId) {
        if(StringUtils.isBlank(transactionId) && !isTransactionIdValid(transactionId)){
            throw new TransactionUncheckedException(TransactionError.ERROR_INVALID_REQ.getDescription(),
                    TransactionError.ERROR_INVALID_REQ.getCode());
        }
        Optional<TransactionEntity> transactionEntity = transactionRepository.findById(transactionId);

        if (!transactionEntity.isPresent() && ObjectUtils.isEmpty(transactionEntity)){
            throw new TransactionRecordNotFoundException();
        }

        return CommonUtils.mapToWrapper(CommonUtils.buildForObjectMapper(TransactionConstant.TRANSACTION_ID, transactionId),
                CommonUtils.buildForObjectMapper(Domain.TRANSACTION.getDescription(),
                        Stream.of(TransactionMapper.mapToTransactionBuilder(transactionEntity.get())).collect(Collectors.toList())));
    }

    @Override
    @Async
    public CompletableFuture<RequestWrapperDTO> addTransaction(String senderId, Transaction transaction) {
        if (!isTransactionValid(transaction)){
            throw new TransactionUncheckedException(TransactionError.ERROR_INVALID_REQ.getDescription()
                    , TransactionError.ERROR_INVALID_REQ.getCode());
        }

        if(!isAccountExist(senderId) || !isAccountExist(transaction.getReceiverAccountId())){
            throw new AccountRecordNotFoundException();
        }

        if(! canUserInitiateMoneyTransfer(transaction)){
            throw  new NotEnoughTransactionBalance(TransactionError.ERROR_NOT_ENOUGH_BALANCE.getDescription(),
                    TransactionError.ERROR_NOT_ENOUGH_BALANCE.getCode());
        }

        try{
            BigDecimal transactionAmount = transaction.getTransactionAmount();

            accountRepository.debitAccount(senderId,transactionAmount);
            accountRepository.creditAccount(transaction.getReceiverAccountId(), transactionAmount);

            TransactionEntity addedEntity = transactionRepository.save(TransactionMapper.mapToTransactionEntity(transaction));

            if(ObjectUtils.isEmpty(addedEntity)){
                throw new TransactionUncheckedException(String.format(TransactionError.ERROR_CREATE.getDescription()
                        , Domain.TRANSACTION.getDescription()), TransactionError.ERROR_CREATE.getCode());
            }

            return CompletableFuture.completedFuture(CommonUtils.mapToWrapper(transaction, CommonUtils.
                    buildForObjectMapper(TransactionConstant.TRANSACTION_FIELD, TransactionMapper.mapToTransactionBuilder(addedEntity))));

        }catch (TransactionUncheckedException ex){
            throw new TransactionUncheckedException(ex.getMessage(), ex.getCode());
        }
    }

    private boolean isTransactionIdValid(String transactionId) {
        return StringUtils.contains(transactionId, SequencePrefix.TRAN.toString());
    }

    private boolean isTransactionValid(Transaction transaction){
        return ObjectUtils.isNotEmpty(transaction)
                && StringUtils.isNotBlank(transaction.getSenderAccountId())
                && StringUtils.isNotBlank(transaction.getReceiverAccountId())
                && ObjectUtils.isNotEmpty(transaction.getTransactionAmount());
    }

    private boolean canUserInitiateMoneyTransfer(Transaction transaction){
        AccountEntity accountEntity = accountRepository.findById(transaction.getSenderAccountId()).get();
        BigDecimal transactionAmount = transaction.getTransactionAmount();
        BigDecimal currentBalance =  accountEntity.getAccountBalance();

        int comparisonResult = currentBalance.compareTo(transactionAmount);

        return comparisonResult > 0? true : false;
    }
    private boolean isAccountExist(String accountId){
        Optional<AccountEntity> accountEntity = accountRepository.findById(accountId);
        if (!accountEntity.isPresent() && ObjectUtils.isEmpty(accountEntity)){
            return false;
        }
        return true;
    }
}

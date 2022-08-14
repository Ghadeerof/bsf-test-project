package com.bsf.service;

import com.bsf.dto.RequestWrapperDTO;
import com.bsf.dto.Transaction;
import com.bsf.entity.TransactionEntity;

import java.util.Date;
import java.util.concurrent.CompletableFuture;

public interface TransactionService {

    RequestWrapperDTO getAllTransactions();

    RequestWrapperDTO getTransactionById(String transactionId);

    CompletableFuture<RequestWrapperDTO> addTransaction(String senderId, Transaction transaction);

}

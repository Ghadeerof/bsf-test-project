package com.bsf.mapper;

import com.bsf.builder.TransactionBuilder;
import com.bsf.dto.Account;
import com.bsf.dto.Transaction;
import com.bsf.entity.AccountEntity;
import com.bsf.entity.TransactionEntity;

import java.util.Date;

public class TransactionMapper {

    private TransactionMapper(){

    }

    public static TransactionBuilder mapToTransactionBuilder(TransactionEntity transactionEntity) {
        return TransactionBuilder.builder()
                .transactionId(transactionEntity.getTransactionId())
                .senderAccountId(transactionEntity.getSenderAccountId())
                .receiverAccountId(transactionEntity.getReceiverAccountId())
                .transactionStatus(transactionEntity.getTransactionStatus())
                .transactionAmount(transactionEntity.getTransactionAmount())
                .createdAt(transactionEntity.getCreatedAt())
                .build();
    }

    public static TransactionEntity mapToTransactionEntity(Transaction transaction){
        TransactionEntity entity = new TransactionEntity();

        entity.setTransactionId(transaction.getTransactionId());
        entity.setSenderAccountId(transaction.getSenderAccountId());
        entity.setReceiverAccountId(transaction.getReceiverAccountId());
        entity.setTransactionAmount(transaction.getTransactionAmount());
        entity.setTransactionStatus(transaction.getTransactionStatus());
        entity.setReason(transaction.getReason());
        entity.setCreatedAt(transaction.getCreatedAt());

        return entity;
    }

    public static Transaction mapToTransactionDto(TransactionEntity transactionEntity){
        Transaction transaction = new Transaction();

        transaction.setTransactionId(transactionEntity.getTransactionId());
        transaction.setSenderAccountId(transactionEntity.getSenderAccountId());
        transaction.setReceiverAccountId(transactionEntity.getReceiverAccountId());
        transaction.setTransactionAmount(transactionEntity.getTransactionAmount());
        transaction.setReason(transactionEntity.getReason());
        transaction.setCreatedAt(transactionEntity.getCreatedAt());

        return transaction;
    }
}

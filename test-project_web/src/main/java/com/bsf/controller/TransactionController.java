package com.bsf.controller;

import com.bsf.dto.Account;
import com.bsf.dto.RequestWrapperDTO;
import com.bsf.dto.Transaction;
import com.bsf.exception.AccountCheckedException;
import com.bsf.exception.AccountRecordNotFoundException;
import com.bsf.exception.TransactionCheckedException;
import com.bsf.exception.TransactionRecordNotFoundException;
import com.bsf.service.TransactionService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;

@RestController
@RequestMapping(value = "/api/v1/transactions")
@CrossOrigin
public class TransactionController {

    private final TransactionService transactionService;

    public TransactionController(TransactionService transactionService){
        this.transactionService = transactionService;
    }

    @GetMapping
    public ResponseEntity<RequestWrapperDTO> getAllTransactions() {
        return new ResponseEntity<>(transactionService.getAllTransactions(), HttpStatus.OK);
    }

    @GetMapping("/{transactionId}")
    public ResponseEntity<RequestWrapperDTO> getTransactionId(@PathVariable("transactionId") String transactionId)
            throws TransactionRecordNotFoundException {
        return new ResponseEntity<>(transactionService.getTransactionById(transactionId), HttpStatus.OK);
    }

    @PostMapping
    public CompletableFuture<ResponseEntity<RequestWrapperDTO>> addTransaction(@RequestParam String senderId, @RequestBody Transaction transaction)
            throws  ExecutionException, InterruptedException {
        CompletableFuture<RequestWrapperDTO> completableFuture = transactionService.addTransaction(senderId,transaction);
        ResponseEntity responseEntity = new ResponseEntity<>(completableFuture.get(), HttpStatus.OK);
        return  CompletableFuture.completedFuture(responseEntity);
    }
}

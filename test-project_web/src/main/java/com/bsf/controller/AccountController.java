package com.bsf.controller;

import com.bsf.dto.Account;
import com.bsf.dto.RequestWrapperDTO;
import com.bsf.exception.AccountCheckedException;
import com.bsf.exception.AccountRecordNotFoundException;
import com.bsf.service.AccountService;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping(value = "/api/v1/accounts")
@CrossOrigin
public class AccountController {

    private final AccountService accountService;

    public AccountController(AccountService accountService){
        this.accountService = accountService;
    }

    @GetMapping
    public ResponseEntity<RequestWrapperDTO> getAllAccounts() {
        return new ResponseEntity<>(accountService.getAllAccounts(), HttpStatus.OK);
    }

    @GetMapping("/{accountId}")
    public ResponseEntity<RequestWrapperDTO> getAccountId(@PathVariable("accountId") String accountId) throws AccountRecordNotFoundException {
        return new ResponseEntity<>(accountService.getAccountById(accountId), HttpStatus.OK);
    }

    @PostMapping
    public ResponseEntity<RequestWrapperDTO> addAccount(@RequestBody Account account) throws AccountCheckedException {
        return new ResponseEntity<>(accountService.addAccount(account), HttpStatus.OK);
    }

    @PutMapping
    public ResponseEntity<RequestWrapperDTO> updateAccount(@RequestBody Account account) throws AccountCheckedException {
        return new ResponseEntity<>(accountService.updateAccount(account), HttpStatus.OK);
    }

    @DeleteMapping("/{accountId}")
    public ResponseEntity<RequestWrapperDTO> deleteAccountById(@PathVariable("accountId") String accountId) throws AccountCheckedException {
        return new ResponseEntity<>(accountService.deleteAccountById(accountId), HttpStatus.OK);
    }


}

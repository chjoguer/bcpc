package com.bcpc.bffcorebank.controller;

import com.bcpc.bffcorebank.domain.Account;
import com.bcpc.bffcorebank.domain.Customer;
import com.bcpc.bffcorebank.service.Account.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/bff/cuenta")
public class AccountController {
    @Autowired
    private AccountService accountService;

    @GetMapping()
    public ResponseEntity<List<Account>> getAllAccounts() {
        List<Account> listAccount = this.accountService.getAllAccounts();
        return ResponseEntity.status(HttpStatus.CREATED).body(listAccount);
    }

    @GetMapping("/{num_account}")
    public ResponseEntity<Optional<Account>> getAllAccounts(@PathVariable("num_account") String num_account) {
        Optional<Account> account = this.accountService.getAccountByNumAccount(num_account);
        return ResponseEntity.status(HttpStatus.CREATED).body(account);
    }

    @PostMapping()
    public ResponseEntity<Account> createAccount(@RequestBody Account account) {
        Account createdAccount = this.accountService.createAccount(account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @PutMapping("/{num_account}")
    public ResponseEntity<Account> updateAccount(@RequestBody Account account,@PathVariable("num_account") String num_account) {
        Account createdAccount = this.accountService.updateAccount(account,num_account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }

    @DeleteMapping("/{num_account}")
    public ResponseEntity<Account> deleteAccount(@PathVariable("num_account") String num_account) {
        Account createdAccount = this.accountService.deleteAccount(num_account);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdAccount);
    }







}

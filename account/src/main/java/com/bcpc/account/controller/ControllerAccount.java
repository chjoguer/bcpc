package com.bcpc.account.controller;

import com.bcpc.account.controller.dto.AccountDTO;
import com.bcpc.account.service.implementation.AccountServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("bcpc/api/cuentas")
public class ControllerAccount {

    @Autowired
    private AccountServiceImpl accountService;

    @GetMapping()
    public ResponseEntity<List<AccountDTO>> fetchAccount() {
        return ResponseEntity.ok(this.accountService.findAll());
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<Optional<AccountDTO>> fetchAccountByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
        return  ResponseEntity.ok(this.accountService.fetchAccountByAccountNumber(accountNumber));
    }

    @PostMapping()
    public ResponseEntity<AccountDTO> createAccount(@RequestBody AccountDTO accountDTO) {
        return ResponseEntity.ok(this.accountService.createAccount(accountDTO));
    }

    @PutMapping("/{accountNumber}")
    public ResponseEntity<AccountDTO> updateClient(@RequestBody AccountDTO accountDTO,@PathVariable String accountNumber) {
        return ResponseEntity.ok(this.accountService.updateAccount(accountDTO,accountNumber));
    }

    @DeleteMapping("/{accountNumber}")
    public ResponseEntity<AccountDTO> deleteAccountByNumberAccount(@PathVariable("accountNumber") String accountNumber) {
        return  ResponseEntity.ok(this.accountService.deleteAccountByNumberAccount(accountNumber));
    }


}

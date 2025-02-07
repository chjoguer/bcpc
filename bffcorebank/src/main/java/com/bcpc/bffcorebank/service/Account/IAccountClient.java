package com.bcpc.bffcorebank.service.Account;

import com.bcpc.bffcorebank.domain.Account;
import com.bcpc.bffcorebank.domain.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;
@Service
//@FeignClient(name = "account-service", url = "http://account-ms:9095")
@FeignClient(name = "account-service", url = "http://localhost:9095")
public interface IAccountClient {

    @GetMapping(value = "/bcpc/api/cuentas", consumes = "application/json")
    List<Account> fetchAccounts();

    @GetMapping(value = "/bcpc/api/cuentas/{number_account}", consumes = "application/json")
    Optional<Account> fetchAccountByNumAccount(@PathVariable("number_account") String number_account);

    @PostMapping(value = "/bcpc/api/cuentas",consumes = "application/json")
    Account createAccount(@RequestBody Account account);

    @PutMapping(value = "/bcpc/api/cuentas/{number_account}",consumes = "application/json")
    Account updateAccount(@RequestBody Account account,@PathVariable("number_account") String number_account);

    @DeleteMapping(value = "/bcpc/api/cuentas/{number_account}",consumes = "application/json")
    Account deleteAccount(@PathVariable("number_account") String number_account);
}
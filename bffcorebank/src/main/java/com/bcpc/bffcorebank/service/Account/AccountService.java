package com.bcpc.bffcorebank.service.Account;
import com.bcpc.bffcorebank.domain.Account;
import com.bcpc.bffcorebank.domain.Customer;
import com.bcpc.bffcorebank.service.Customer.ICustomerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;
import java.util.Optional;

// In a real microservices environment, you can remove the 'url' property and use Eureka for discovery.
@Service
public class AccountService {

    @Autowired
    private  IAccountClient accountClient;

    @Autowired
    public AccountService(IAccountClient accountClient) {
        this.accountClient = accountClient;
    }

    public Account createAccount(Account account) {
        Account createdAccount = accountClient.createAccount(account);
        return createdAccount;
    }


    public Account deleteAccount(String num_account) {
        Account updatedAccount = accountClient.deleteAccount(num_account);
        return updatedAccount;
    }

    public Account updateAccount(Account account,String num_account) {
        Account updatedAccount = accountClient.updateAccount(account,num_account);
        return updatedAccount;
    }

    public List<Account> getAllAccounts() {
        return this.accountClient.fetchAccounts();
    }

    public Optional<Account> getAccountByNumAccount(String num_account ) {
        return this.accountClient.fetchAccountByNumAccount(num_account);
    }
}
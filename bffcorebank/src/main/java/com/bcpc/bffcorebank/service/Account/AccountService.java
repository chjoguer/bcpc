package com.bcpc.bffcorebank.service.Account;
import com.bcpc.bffcorebank.domain.Account;
import com.bcpc.bffcorebank.service.FacadeCore.FacadeCoreService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;

// In a real microservices environment, you can remove the 'url' property and use Eureka for discovery.
@Service
public class AccountService {

    @Autowired
    private  IAccountClient accountClient;
    @Autowired
    private FacadeCoreService facadeCoreLogic;

    @Autowired
    public AccountService(IAccountClient accountClient) {
        this.accountClient = accountClient;
    }

    public Account createAccount(Account account) {
        Account acc = this.facadeCoreLogic.processCreateAccount(account);
        return accountClient.createAccount(acc);
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
package com.bcpc.bffcorebank.service.Customer;


import com.bcpc.bffcorebank.domain.Account;
import com.bcpc.bffcorebank.domain.Customer;
import com.bcpc.bffcorebank.service.Account.IAccountClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.List;
import java.util.Random;
import java.util.UUID;

@Service
public class CustomerService {

    private final ICustomerClient customerClient;
    private final IAccountClient accountClient;

    @Autowired
    public CustomerService(ICustomerClient customerClient, IAccountClient accountClient) {
        this.customerClient = customerClient;
        this.accountClient = accountClient;
    }

    public static String generateSixDigitAccountNumber() {
        SecureRandom secureRandom = new SecureRandom();
        int number = 100000 + secureRandom.nextInt(900000); // generates a number between 100000 and 999999
        return String.valueOf(number);
    }

    public Customer createCustomer(Customer customer) {
        Customer createdCustomer = customerClient.createCustomer(customer);
        String accountNumber = generateSixDigitAccountNumber();

        Account account = new Account();
        account.setNumberAccount(accountNumber);
        account.setTypeAccount("Ahorro");
        account.setInitialAmount(0.0);
        account.setStatus(1);
        accountClient.createAccount(account);

        return createdCustomer;
    }


    public List<Customer> getCustomers() {
        return this.customerClient.fetchCustomers();
    }
}
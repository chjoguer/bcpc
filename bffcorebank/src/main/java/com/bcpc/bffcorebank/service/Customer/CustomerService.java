package com.bcpc.bffcorebank.service.Customer;


import com.bcpc.bffcorebank.domain.Account;
import com.bcpc.bffcorebank.domain.Customer;
import com.bcpc.bffcorebank.service.Account.IAccountClient;
import com.bcpc.bffcorebank.service.FacadeCore.FacadeCoreService;
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
    private FacadeCoreService facadeCoreLogic;

    @Autowired
    public CustomerService(ICustomerClient customerClient, IAccountClient accountClient) {
        this.customerClient = customerClient;
        this.accountClient = accountClient;
    }



    public Customer createCustomer(Customer customer) {
        Customer createdCustomer = customerClient.createCustomer(customer);

        Account account = new Account();
        account.setIdentification(customer.getIdentification());
        account.setTypeAccount("0");

        Account acc = this.facadeCoreLogic.processCreateAccount(account);

        accountClient.createAccount(acc);

        return createdCustomer;
    }




    public List<Customer> getCustomers() {
        return this.customerClient.fetchCustomers();
    }

    public Customer updateCustomerByIdentification(String identification,Customer customer) {
        return customerClient.updateCustomer(identification,customer);
    }

    public Customer getCustomersByIdentification(String identification) {
        return this.customerClient.fetchClientByIdentification(identification);
    }



    public Customer deleteCustomer(String identification) {
        return this.customerClient.deleteCustomer(identification);
    }

    public static String generateSixDigitAccountNumber() {
        SecureRandom secureRandom = new SecureRandom();
        int number = 100000 + secureRandom.nextInt(900000);
        return String.valueOf(number);
    }
}
package com.bcpc.bffcorebank.controller;

import com.bcpc.bffcorebank.domain.Customer;
import com.bcpc.bffcorebank.service.Customer.CustomerService;
import com.bcpc.bffcorebank.service.Customer.ICustomerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bff/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;

    @PostMapping("/create")
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = this.customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @GetMapping()
    public ResponseEntity<List<Customer>> fetchCustomer() {
        List<Customer> createdCustomer = this.customerService.getCustomers();
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

}
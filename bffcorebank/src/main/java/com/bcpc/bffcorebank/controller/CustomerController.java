package com.bcpc.bffcorebank.controller;

import com.bcpc.bffcorebank.domain.Customer;
import com.bcpc.bffcorebank.service.Customer.CustomerService;
import com.bcpc.bffcorebank.service.Customer.ICustomerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import javax.management.DescriptorKey;
import java.util.List;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/bff/customers")
public class CustomerController {

    @Autowired
    private CustomerService customerService;



    @GetMapping()
    public ResponseEntity<List<Customer>> fetchCustomer() {
        List<Customer> createdCustomer = this.customerService.getCustomers();
        return ResponseEntity.status(HttpStatus.OK).body(createdCustomer);
    }

    @GetMapping("/{identification}")
    public ResponseEntity<Customer> fetchClientByIdentification(@PathVariable("identification") String identification) {
        return  ResponseEntity.ok(this.customerService.getCustomersByIdentification(identification));
    }


    @PostMapping()
    public ResponseEntity<Customer> registerCustomer(@RequestBody Customer customer) {
        Customer createdCustomer = this.customerService.createCustomer(customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdCustomer);
    }

    @PutMapping("/{identification}")
    public ResponseEntity<Customer> updateCustomer(@PathVariable("identification") String identification,@RequestBody Customer customer) {
        Customer updatedCustomer = this.customerService.updateCustomerByIdentification(identification,customer);
        return ResponseEntity.status(HttpStatus.CREATED).body(updatedCustomer);
    }


    @DeleteMapping("/{identification}")
    public ResponseEntity<Customer> deleteCustomer(@PathVariable("identification") String identification) {
        Customer createdCustomer = this.customerService.deleteCustomer(identification);
        return ResponseEntity.status(HttpStatus.ACCEPTED).body(createdCustomer);
    }


}
package com.bcpc.bffcorebank.service.Customer;


import com.bcpc.bffcorebank.domain.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@FeignClient(name = "customer-service", url = "http://customer-ms:9090")
public interface ICustomerClient {

    @GetMapping(value = "/bcpc/api/clientes", consumes = "application/json")
    List<Customer> fetchCustomers();

    @GetMapping(value = "/bcpc/api/clientes/{identification}", consumes = "application/json")
    Customer fetchClientByIdentification(@PathVariable("identification") String identification);




    @PostMapping(value = "/bcpc/api/clientes/create", consumes = "application/json")
    Customer createCustomer(@RequestBody Customer customer);

    // Add other endpoints as needed.
}

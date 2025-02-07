package com.bcpc.bffcorebank.service.Customer;


import com.bcpc.bffcorebank.domain.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@Service
//@FeignClient(name = "customer-service", url = "http://customer-ms:9090")
@FeignClient(name = "customer-service", url = "http://localhost:9090")
public interface ICustomerClient {

    @GetMapping(value = "/bcpc/api/clientes", consumes = "application/json")
    List<Customer> fetchCustomers();

    @GetMapping(value = "/bcpc/api/clientes/{identification}", consumes = "application/json")
    Customer fetchClientByIdentification(@PathVariable("identification") String identification);

    @PostMapping(value = "/bcpc/api/clientes", consumes = "application/json")
    Customer createCustomer(@RequestBody Customer customer);

    @PutMapping(value = "/bcpc/api/clientes/{identification}", consumes = "application/json")
    Customer updateCustomer(@PathVariable("identification") String identification,@RequestBody Customer customer);

    @DeleteMapping(value = "/bcpc/api/clientes/{identification}", consumes = "application/json")
    Customer deleteCustomer(@PathVariable("identification") String identification);

}

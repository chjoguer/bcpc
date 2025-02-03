package com.bcpc.bffcorebank.service.Customer;


import com.bcpc.bffcorebank.domain.Customer;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

// In a real microservices environment, you can remove the 'url' property and use Eureka for discovery.
@Service
@FeignClient(name = "customer-service", url = "http://localhost:9090")
public interface ICustomerClient {

    @GetMapping(value = "/bcpc/api/clientes", consumes = "application/json")
    List<Customer> fetchCustomers();

    @PostMapping(value = "/bcpc/api/clientes/create", consumes = "application/json")
    Customer createCustomer(@RequestBody Customer customer);

    // Add other endpoints as needed.
}

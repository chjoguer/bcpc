package com.bcpc.bffcorebank.controller;

import com.bcpc.bffcorebank.domain.Customer;
import com.bcpc.bffcorebank.domain.Movement;
import com.bcpc.bffcorebank.service.Customer.CustomerService;
import com.bcpc.bffcorebank.service.Movement.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

@RestController
@RequestMapping("/bff/movimiento")
public class MovementController {


    @Autowired
    private MovementService movementService;

    @PostMapping()
    public ResponseEntity<Movement> createMovement(@RequestBody Movement movement) {
        Movement createdMovement = this.movementService.createMovement(movement);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovement);
    }


}

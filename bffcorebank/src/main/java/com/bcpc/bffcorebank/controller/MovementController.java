package com.bcpc.bffcorebank.controller;

import com.bcpc.bffcorebank.domain.Account;
import com.bcpc.bffcorebank.domain.Customer;
import com.bcpc.bffcorebank.domain.Movement;
import com.bcpc.bffcorebank.service.Customer.CustomerService;
import com.bcpc.bffcorebank.service.Movement.MovementService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
@RequestMapping("/bff/movimiento")
public class MovementController {


    @Autowired
    private MovementService movementService;

    @GetMapping()
    public ResponseEntity<List<Movement>> fetchMovement() {
        List<Movement> createdCustomer = this.movementService.getMovements();
        return ResponseEntity.status(HttpStatus.OK).body(createdCustomer);
    }

    @GetMapping("/{num_account}")
    public ResponseEntity<List<Movement>> getAllAccounts(@PathVariable("num_account") String num_account) {
        List<Movement> account = this.movementService.getMovementsByNumAccount(num_account);
        return ResponseEntity.status(HttpStatus.OK).body(account);
    }

    @PostMapping()
    public ResponseEntity<Movement> createMovement(@RequestBody Movement movement) {
        Movement createdMovement = this.movementService.createMovement(movement);
        return ResponseEntity.status(HttpStatus.CREATED).body(createdMovement);
    }

    @DeleteMapping("/{movement_id}")
    public ResponseEntity<Optional<Movement>> deleteMovementId(@PathVariable("movement_id") Long movement_id) {
        System.out.println(movement_id);
        Optional<Movement> movement = this.movementService.deleteMovementById(movement_id);
        return ResponseEntity.status(HttpStatus.OK).body(movement);
    }



}

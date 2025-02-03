package com.bcpc.movement.controller;


import com.bcpc.movement.controller.dto.MovementDTO;
import com.bcpc.movement.service.Implementations.MovementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("bcpc/api/movimiento")
public class MovementController {

    @Autowired
    private MovementServiceImpl movementService;

    @GetMapping
    public ResponseEntity<List<MovementDTO>> fetchAccount() {
        return ResponseEntity.ok(this.movementService.findAll());
    }

    @GetMapping("/{accountNumber}")
    public ResponseEntity<List<MovementDTO>> fetchMovementByAccountNumber(@PathVariable("accountNumber") String accountNumber) {
        return  ResponseEntity.ok(this.movementService.findMovmentByAccountNumber(accountNumber));
    }

    @PostMapping("/create")
    public ResponseEntity<MovementDTO> createMovement(@RequestBody MovementDTO movementDTO) {
        return ResponseEntity.ok(this.movementService.createMovement(movementDTO));
    }

    @PutMapping("/{identification}")
    public ResponseEntity<MovementDTO> updateClient(@RequestBody MovementDTO movementDTO,@PathVariable String identification) {
        return ResponseEntity.ok(this.movementService.updateMovement(movementDTO,identification));
    }


}

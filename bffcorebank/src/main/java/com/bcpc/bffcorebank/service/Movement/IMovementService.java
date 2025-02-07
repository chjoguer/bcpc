package com.bcpc.bffcorebank.service.Movement;

import com.bcpc.bffcorebank.domain.Movement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.*;

import java.time.LocalDate;
import java.util.Base64;
import java.util.List;
import java.util.Optional;

@Service
//@FeignClient(name = "movement-service", url = "http://movement-ms:9080")
@FeignClient(name = "movement-service", url = "http://localhost:9080")
public interface IMovementService {

    @GetMapping(value = "bcpc/api/movimiento", consumes = "application/json")
    List<Movement> fetchMovement();

    @GetMapping(value = "bcpc/api/movimiento/{accountNumber}", consumes = "application/json")
    List<Movement> getMovementsByNumAccount(@PathVariable("accountNumber") String accountNumber);

    @PostMapping(value = "bcpc/api/movimiento/create", consumes = "application/json")
    Movement createMovement(@RequestBody Movement movementDTO);

    @PutMapping(value = "bcpc/api/movimiento/{movementId}", consumes = "application/json")
    Movement updateMovement(@RequestBody Movement movementDTO ,@PathVariable Long movementId);

    @DeleteMapping(value = "bcpc/api/movimiento/{movementId}", consumes = "application/json")
    Optional<Movement> deleteMovement(@PathVariable Long movementId);

    @GetMapping("/{accountNumber}/report")
    ResponseEntity<?> fetchMovementByAccountAndDate(
            @PathVariable("accountNumber") String accountNumber,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(value = "exportType", required = false) String exportType);

}

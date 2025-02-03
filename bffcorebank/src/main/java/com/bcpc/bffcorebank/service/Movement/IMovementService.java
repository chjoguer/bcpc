package com.bcpc.bffcorebank.service.Movement;

import com.bcpc.bffcorebank.domain.Movement;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import java.util.List;

@Service
@FeignClient(name = "movement-service", url = "http://localhost:9080")
public interface IMovementService {

    @GetMapping(value = "bcpc/api/movimiento", consumes = "application/json")
    List<Movement> fetchMovement();

    @PostMapping(value = "bcpc/api/movimiento/create", consumes = "application/json")
    Movement createMovement(@RequestBody Movement movement);
}

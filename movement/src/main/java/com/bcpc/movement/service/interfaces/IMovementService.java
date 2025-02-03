package com.bcpc.movement.service.interfaces;

import com.bcpc.movement.controller.dto.MovementDTO;

import java.util.List;
import java.util.Optional;

public interface IMovementService {
    List<MovementDTO> findAll();
    List<MovementDTO> findMovmentByAccountNumber(String identification);
    MovementDTO createMovement(MovementDTO person);
    MovementDTO updateMovement(MovementDTO person,String identification);
    String deleteAccount(String identification);
}

package com.bcpc.movement.repository;

import com.bcpc.movement.controller.dto.MovementDTO;
import com.bcpc.movement.domain.Movement;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface MovementRepository  extends JpaRepository<Movement, Long> {
    List<Movement> findBynumberAccount(String number_account);

}

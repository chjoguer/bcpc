package com.bcpc.movement.service.Implementations;

import com.bcpc.movement.controller.dto.MovementDTO;
import com.bcpc.movement.domain.Movement;
import com.bcpc.movement.repository.MovementRepository;
import com.bcpc.movement.service.interfaces.IMovementService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class MovementServiceImpl implements IMovementService {
    @Autowired
    MovementRepository movementRepository;

    @Override
    public List<MovementDTO> findAll() {
        ModelMapper modelMapper = new ModelMapper();

        return this.movementRepository.findAll()
                .stream()
                .map(entity -> modelMapper.map(entity, MovementDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public List<MovementDTO> findMovmentByAccountNumber(String number_account) {
        ModelMapper modelMapper = new ModelMapper();

        return this.movementRepository.findBynumberAccount(number_account).stream()
                .map(entity -> modelMapper.map(entity, MovementDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public MovementDTO createMovement(MovementDTO movementDTO) {

        try{

            ModelMapper modelMapper = new ModelMapper();
            Movement newMovement = modelMapper.map(movementDTO, Movement.class);
            System.out.println("Object"+newMovement);
            this.movementRepository.save(newMovement);
            return movementDTO;

        }catch (Exception e){

            System.out.println(e.getMessage());
            throw new UnsupportedOperationException("Error al guardar la cuenta");
        }
    }

    @Override
    public MovementDTO updateMovement(MovementDTO movementDTO, String identification) {
        return null;
    }

    @Override
    public String deleteAccount(String identification) {
        return "";
    }
}

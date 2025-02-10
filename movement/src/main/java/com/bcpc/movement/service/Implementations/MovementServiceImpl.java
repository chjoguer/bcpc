package com.bcpc.movement.service.Implementations;

import com.bcpc.movement.bean.PdfUtils;
import com.bcpc.movement.controller.dto.MovementDTO;
import com.bcpc.movement.controller.dto.ReportDTO;
import com.bcpc.movement.domain.Movement;
import com.bcpc.movement.repository.MovementRepository;
import com.bcpc.movement.service.interfaces.IMovementService;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;


import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.io.ByteArrayOutputStream;
import java.io.IOException;
import java.time.LocalDate;
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

        return this.movementRepository.findAllMovements()
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

    public List<ReportDTO> findMovementByAccountAndDate(String accountNumber, LocalDate startDate, LocalDate endDate) {
        return this.movementRepository.findMovementByAccountAndDate(accountNumber, startDate, endDate);
    }

    @Override
    public List<ReportDTO> findMovementsByDate(LocalDate startDate, LocalDate endDate) {
        return this.movementRepository.findMovementsByDate(startDate, endDate);
    }



    @Override
    public MovementDTO updateMovement(MovementDTO movementDTO, Long id_movement) {
        ModelMapper modelMapper = new ModelMapper();

        Optional<Movement> cMovement = this.movementRepository.findById(id_movement);

        if (cMovement.isPresent()) {
            Movement currentAccount = cMovement.get();
            currentAccount.setStatus(movementDTO.getStatus());
            currentAccount.setInitialAmount(movementDTO.getInitialAmount());

            this.movementRepository.save(currentAccount);
            return modelMapper.map(currentAccount, MovementDTO.class);
        }else{
            return new MovementDTO();
        }
    }



    @Override
    public MovementDTO deleteMovementById(Long id_movement) {
        ModelMapper modelMapper = new ModelMapper();

        Optional<Movement> cMovement = this.movementRepository.findById(id_movement);

        if (cMovement.isPresent()) {
            Movement currentAccount = cMovement.get();
            currentAccount.setStatus(0);

            this.movementRepository.save(currentAccount);
            return modelMapper.map(currentAccount, MovementDTO.class);
        }else{
            return new MovementDTO();
        }
    }

    @Override
    public byte[] generateReportPdf(List<ReportDTO> reports) throws DocumentException, IOException {
        return PdfUtils.generateReportPdf(reports);

    }
}

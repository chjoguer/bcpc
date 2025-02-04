package com.bcpc.movement.service.Implementations;

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

    public List<ReportDTO> findMovementByAccountAndDate(String accountNumber, LocalDate startDate, LocalDate endDate) {
        return this.movementRepository.findMovementByAccountAndDate(accountNumber, startDate, endDate);
//        ModelMapper modelMapper = new ModelMapper();
//
//        return this.movementRepository.findMovementByAccountAndDate(accountNumber, startDate, endDate).stream()
//                .map(entity -> modelMapper.map(entity, ReportDTO.class))
//                .collect(Collectors.toList());
    }

    @Override
    public MovementDTO updateMovement(MovementDTO movementDTO, String identification) {
        return null;
    }

    @Override
    public String deleteAccount(String identification) {
        return "";
    }

    @Override
    public byte[] generateReportPdf(List<ReportDTO> reports) throws DocumentException {
        Document document = new Document();
        ByteArrayOutputStream baos = new ByteArrayOutputStream();

        PdfWriter.getInstance(document, baos);
        document.open();

        document.add(new Paragraph("Movement Report"));
        document.add(new Paragraph(" ")); // Empty line

        // Iterate over each report item and add details to the PDF.
        for (ReportDTO report : reports) {
            // Format your report content as needed.
            String reportLine = String.format("Date: %s, Name: %s, Account: %s, Type: %s, Status: %s, " +
                            "Initial Amount: %s, Movement Amount: %s, Total: %s",
                    report.getMovementAt(),
                    report.getName(),
                    report.getNumberAccount(),
                    report.getTypeAccount(),
                    report.getStatus(),
                    report.getInitialAmount(),
                    report.getMovementAmount(),
                    report.getTotalAmount());
            document.add(new Paragraph(reportLine));
        }

        document.close();
        return baos.toByteArray();
    }
}

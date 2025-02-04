package com.bcpc.movement.controller;


import com.bcpc.movement.bean.CustomResponse;
import com.bcpc.movement.controller.dto.MovementDTO;
import com.bcpc.movement.controller.dto.ReportDTO;
import com.bcpc.movement.service.Implementations.MovementServiceImpl;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import com.itextpdf.text.Document;
import com.itextpdf.text.DocumentException;
import com.itextpdf.text.Paragraph;
import com.itextpdf.text.pdf.PdfWriter;
import java.util.Base64 ;

import java.time.LocalDate;
import java.util.List;
import java.util.Optional;

@RestController
@CrossOrigin(origins = "http://localhost:4200")
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

    @GetMapping("/{accountNumber}/report")
    public ResponseEntity<?> fetchMovementByAccountAndDate(
            @PathVariable("accountNumber") String accountNumber,
            @RequestParam("startDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate startDate,
            @RequestParam("endDate") @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate endDate,
            @RequestParam(value = "exportType", required = false) String exportType)  {

        List<ReportDTO> reportData = this.movementService.findMovementByAccountAndDate(accountNumber, startDate, endDate);

        if ("pdf".equalsIgnoreCase(exportType)) {
            try {
                byte[] pdfBytes = this.movementService.generateReportPdf(reportData);
                String base64Pdf = Base64.getEncoder().encodeToString(pdfBytes);

                return CustomResponse.success(base64Pdf);
            } catch (DocumentException e) {
                return ResponseEntity.status(500).body("Error generating PDF: " + e.getMessage());
            }
        }
        return CustomResponse.success(reportData);
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

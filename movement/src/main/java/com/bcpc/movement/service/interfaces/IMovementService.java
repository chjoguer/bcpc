package com.bcpc.movement.service.interfaces;

import com.bcpc.movement.controller.dto.MovementDTO;
import com.bcpc.movement.controller.dto.ReportDTO;
import com.itextpdf.text.DocumentException;


import java.util.List;

public interface IMovementService {
    List<MovementDTO> findAll();
    List<MovementDTO> findMovmentByAccountNumber(String id_movement);
    MovementDTO createMovement(MovementDTO person);
    MovementDTO updateMovement(MovementDTO person,Long id_movement);
    MovementDTO deleteMovementById(Long id_movement);
    byte[] generateReportPdf(List<ReportDTO> reports) throws DocumentException;

}

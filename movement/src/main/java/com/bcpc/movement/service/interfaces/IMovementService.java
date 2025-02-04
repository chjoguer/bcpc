package com.bcpc.movement.service.interfaces;

import com.bcpc.movement.controller.dto.MovementDTO;
import com.bcpc.movement.controller.dto.ReportDTO;
import com.itextpdf.text.DocumentException;


import java.util.List;

public interface IMovementService {
    List<MovementDTO> findAll();
    List<MovementDTO> findMovmentByAccountNumber(String identification);
    MovementDTO createMovement(MovementDTO person);
    MovementDTO updateMovement(MovementDTO person,String identification);
    String deleteAccount(String identification);
    byte[] generateReportPdf(List<ReportDTO> reports) throws DocumentException;

}

package com.bcpc.movement.controller.dto;

import lombok.*;

import java.sql.Timestamp;
import java.time.LocalDate;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ReportDTO {

    private Timestamp movementAt;
    private String name;
    private String numberAccount;
    private String typeAccount;
    private Integer status;
    private Double initialAmount;
    private Double movementAmount;
    private Double totalAmount;

}
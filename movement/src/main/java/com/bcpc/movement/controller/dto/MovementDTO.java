package com.bcpc.movement.controller.dto;

import jakarta.persistence.Column;
import lombok.*;

import java.util.Date;


@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class MovementDTO {
    private Long id;
    private String numberAccount;
//    private Date movementAt;
    private String typeMovement;
    private Double initialAmount;
    private Double movementAmount;
    private Integer status;


}

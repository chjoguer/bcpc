package com.bcpc.movement.domain;


import jakarta.persistence.*;
import lombok.*;

import java.util.Date;

@Entity
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "movement")
public class Movement {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_account") // Ensure this is the correct column name in the database
    private String numberAccount;
//    @Column(name = "movement_at")
//    private Date movementAt;
    @Column(name = "type_movement")
    private String typeMovement;
    @Column(name = "initial_amount")
    private Double initialAmount;
    @Column(name = "movement_amount")
    private Double movementAmount;
    private Integer status;
}

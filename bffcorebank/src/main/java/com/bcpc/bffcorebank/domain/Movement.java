package com.bcpc.bffcorebank.domain;

import lombok.*;


@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Movement {
    private Long id;
    private String numberAccount;
    //    private Date movementAt;
    private String typeMovement;
    private Double initialAmount;
    private Double movementAmount;
    private Integer status;


}

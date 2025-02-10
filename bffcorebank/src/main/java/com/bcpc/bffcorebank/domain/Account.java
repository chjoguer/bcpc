package com.bcpc.bffcorebank.domain;

import lombok.*;

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@ToString
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Account {
    private String numberAccount;
    private String identification;
    private String typeAccount;
    private Double initialAmount;
    private Integer status;

}
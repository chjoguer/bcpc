package com.bcpc.account.controller.dto;
import lombok.*;

import java.io.Serializable;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class AccountDTO implements Serializable {
    private String numberAccount;
    private String typeAccount;
    private Double initialAmount;
    private Integer status;
}

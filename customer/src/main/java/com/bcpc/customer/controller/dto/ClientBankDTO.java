package com.bcpc.customer.controller.dto;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientBankDTO {
    private String name;
    private String gender;
    private Integer age;
    private String identification;
    private String address;
    private String phone;
    private String password;
    private String clientReference;
    private String status;
}

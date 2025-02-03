package com.bcpc.customer.controller.dto;

import lombok.*;

@Getter
@Setter
@Data
@AllArgsConstructor
@NoArgsConstructor
public class ClientDTO {
    private String name;
    private String gender;
    private Integer age;
    private String identification;
}

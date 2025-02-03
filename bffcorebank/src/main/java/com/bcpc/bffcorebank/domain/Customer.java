package com.bcpc.bffcorebank.domain;

import lombok.*;

@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Customer {
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

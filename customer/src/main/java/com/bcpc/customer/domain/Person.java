package com.bcpc.customer.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "person")
public class Person {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String gender;
    private Integer age;
    private String identification;
    private String address;
    private String phone;


}
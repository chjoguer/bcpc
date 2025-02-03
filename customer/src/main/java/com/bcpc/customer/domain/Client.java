package com.bcpc.customer.domain;

import jakarta.persistence.*;
import lombok.*;

@Getter
@Setter
@Builder
@Entity
@ToString
@AllArgsConstructor
@NoArgsConstructor
@Table(name = "client")
public class Client {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String name;
    private String gender;
    private String identification;
    private Integer age;
}
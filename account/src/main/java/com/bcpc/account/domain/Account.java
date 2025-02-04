package com.bcpc.account.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "account")
public class Account  {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;

    @Column(name = "number_account") // Ensure this is the correct column name in the database
    private String numberAccount;
    @Column(name = "type_account")
    private String typeAccount;
    private Double initialAmount;
    private Integer status;
    private String identification;

}
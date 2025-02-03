package com.bcpc.customer.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "client2")
public class Client2 extends Person {
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        private Long id;
        @Column(name = "person_id")
        private String clientReference;
        private String password;
        private String status;




}


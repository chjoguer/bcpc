package com.bcpc.customer.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity
@Setter(AccessLevel.PUBLIC)
@Getter(AccessLevel.PUBLIC)
@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
@Inheritance(strategy = InheritanceType.JOINED)
@Table(name = "client")
public class Client extends Person {
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        private Long id;
        @Column(name = "person_id")
        private String clientReference;
        private String password;
        private String status;




}


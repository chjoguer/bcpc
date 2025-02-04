package com.bcpc.customer.domain;

import jakarta.persistence.*;
import lombok.*;

@Entity

@Table(name = "client")
public class Client extends Person {
//        @Id
//        @GeneratedValue(strategy = GenerationType.IDENTITY)
//        private Long id;
        @Column(name = "person_id")
        private String clientReference;
        private String password;
        private String status;

        public String getClientReference() {
                return clientReference;
        }

        public void setClientReference(String clientReference) {
                this.clientReference = clientReference;
        }

        public String getPassword() {
                return password;
        }

        public void setPassword(String password) {
                this.password = password;
        }

        public String getStatus() {
                return status;
        }

        public void setStatus(String status) {
                this.status = status;
        }
}


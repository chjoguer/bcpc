package com.bcpc.customer.repository;

import com.bcpc.customer.domain.Person;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ClientRepository extends JpaRepository<Person, Long> {}

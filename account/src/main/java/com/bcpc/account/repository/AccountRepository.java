package com.bcpc.account.repository;

import com.bcpc.account.controller.dto.AccountDTO;
import com.bcpc.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    Optional<Account> findBynumberAccount(String number_account);


}

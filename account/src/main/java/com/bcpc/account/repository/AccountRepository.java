package com.bcpc.account.repository;

import com.bcpc.account.controller.dto.AccountDTO;
import com.bcpc.account.domain.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface AccountRepository extends JpaRepository<Account, Long> {
    @Query(value = "select * from account a where status =1",nativeQuery = true)
    List<Account> findAllAccount();
    Optional<Account> findBynumberAccount(String number_account);




}

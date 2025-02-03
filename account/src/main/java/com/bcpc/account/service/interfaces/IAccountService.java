package com.bcpc.account.service.interfaces;

import com.bcpc.account.controller.dto.AccountDTO;

import java.util.List;
import java.util.Optional;

public interface IAccountService {

    List<AccountDTO> findAll();
    Optional<AccountDTO> findAccountById(String identification);
    AccountDTO createAccount(AccountDTO person);
    AccountDTO updateAccount(AccountDTO person,String identification);
    String deleteAccount(String identification);

}

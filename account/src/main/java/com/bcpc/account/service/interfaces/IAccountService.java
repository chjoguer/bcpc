package com.bcpc.account.service.interfaces;

import com.bcpc.account.controller.dto.AccountDTO;

import java.util.List;
import java.util.Optional;

public interface IAccountService {

    List<AccountDTO> findAll();
    Optional<AccountDTO> fetchAccountByAccountNumber(String identification);
    AccountDTO createAccount(AccountDTO account);
    AccountDTO updateAccount(AccountDTO account,String identification);
    AccountDTO deleteAccountByNumberAccount(String identification);

}

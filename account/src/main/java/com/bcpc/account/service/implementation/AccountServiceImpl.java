package com.bcpc.account.service.implementation;

import com.bcpc.account.controller.dto.AccountDTO;
import com.bcpc.account.domain.Account;
import com.bcpc.account.repository.AccountRepository;
import com.bcpc.account.service.interfaces.IAccountService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;

@Service
public class AccountServiceImpl implements IAccountService {

    @Autowired
    private AccountRepository accountRepository;



    @Override
    public List<AccountDTO> findAll() {
        ModelMapper modelMapper = new ModelMapper();

        return this.accountRepository.findAllAccount()
                .stream()
                .map(entity -> modelMapper.map(entity, AccountDTO.class))
                .collect(Collectors.toList());
    }

    @Override
    public Optional<AccountDTO> fetchAccountByAccountNumber(String number_account) {
        ModelMapper modelMapper = new ModelMapper();

        return this.accountRepository.findBynumberAccount(number_account).stream()
                .map(entity -> modelMapper.map(entity, AccountDTO.class))
                .findFirst();
    }

    @Override
    public AccountDTO createAccount(AccountDTO accountDTO) {


        try{

            ModelMapper modelMapper = new ModelMapper();
            Account newAccount = modelMapper.map(accountDTO, Account.class);
            System.out.println("Object"+newAccount);
            this.accountRepository.save(newAccount);

            return accountDTO;

        }catch (Exception e){

            System.out.println(e.getMessage());
            throw new UnsupportedOperationException("Error al guardar la cuenta");
        }
    }

    @Override
    public AccountDTO updateAccount(AccountDTO accountDTO, String number_account) {
        ModelMapper modelMapper = new ModelMapper();


        Optional<Account> cAccount = this.accountRepository.findBynumberAccount(number_account);

        if (cAccount.isPresent()) {
            Account currentAccount = cAccount.get();
            currentAccount.setStatus(accountDTO.getStatus());
            currentAccount.setInitialAmount(accountDTO.getInitialAmount());

            this.accountRepository.save(currentAccount);
            return modelMapper.map(currentAccount, AccountDTO.class);
        }else{
            return new AccountDTO();
        }
    }

    @Override
    public AccountDTO deleteAccountByNumberAccount(String number_account) {
        ModelMapper modelMapper = new ModelMapper();
        Optional<Account> cAccount = this.accountRepository.findBynumberAccount(number_account);

        if (cAccount.isPresent()) {
            Account currentAccount = cAccount.get();
            currentAccount.setStatus(0);
            this.accountRepository.save(currentAccount);
            return modelMapper.map(currentAccount, AccountDTO.class);
        }else{
            return new AccountDTO();
        }
    }


}

package com.bcpc.bffcorebank.service.FacadeCore;

import com.bcpc.bffcorebank.domain.Account;
import com.bcpc.bffcorebank.domain.Customer;
import org.springframework.stereotype.Service;

import java.security.SecureRandom;
import java.util.Objects;

@Service
public class FacadeCoreService implements IFacede{

    enum TypeAccount {
        AHORRO_PLUS,
        CORRIENTE,
        AHORRO;
    }

    @Override
    public int inactiveClient() {
        return 0;
    }
    @Override
    public Account processCreateAccount(Account account){
        account.setNumberAccount(generateSixDigitAccountNumber());
        if (Objects.equals(account.getTypeAccount(), "0"))
            account.setTypeAccount(TypeAccount.AHORRO.name());
        if (Objects.equals(account.getTypeAccount(), "1"))
            account.setTypeAccount(TypeAccount.AHORRO_PLUS.name());
        if (Objects.equals(account.getTypeAccount(), "2"))
            account.setTypeAccount(TypeAccount.CORRIENTE.name());
        account.setInitialAmount(0.0);
        account.setStatus(1);
        return account;
    }




    public static String generateSixDigitAccountNumber() {
        SecureRandom secureRandom = new SecureRandom();
        int number = 100000 + secureRandom.nextInt(900000);
        return String.valueOf(number);
    }

}

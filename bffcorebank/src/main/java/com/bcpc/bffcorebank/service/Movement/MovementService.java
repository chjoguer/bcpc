package com.bcpc.bffcorebank.service.Movement;

import com.bcpc.bffcorebank.domain.Account;
import com.bcpc.bffcorebank.domain.Customer;
import com.bcpc.bffcorebank.domain.Movement;
import com.bcpc.bffcorebank.service.Account.IAccountClient;
import com.bcpc.bffcorebank.service.Customer.ICustomerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.Optional;

@Service
public class MovementService {

    private final IMovementService movementClient;
    private final IAccountClient accountClient;

    @Autowired
    public MovementService(IAccountClient accountClient, IMovementService movementClient) {
        this.accountClient = accountClient;
        this.movementClient = movementClient;
    }

    public Movement createMovement(Movement movement) {
        Optional<Account> account = this.accountClient.fetchAccountByNumAccount(movement.getNumberAccount());

        if(account.isPresent()){
            Account ac = account.get();
            System.out.println(movement.getInitialAmount());
            Double initialAmount = ac.getInitialAmount();


            movement.setInitialAmount(initialAmount);
            Movement newMovement = this.movementClient.createMovement(movement);
            ac.setInitialAmount(ac.getInitialAmount()+newMovement.getMovementAmount());
            Account updatedAccount = this.accountClient.updateAccount(ac,ac.getNumberAccount());
            return newMovement;
        }
        return null;

    }




}

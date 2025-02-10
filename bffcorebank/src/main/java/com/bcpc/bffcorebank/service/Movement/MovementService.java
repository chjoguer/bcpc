package com.bcpc.bffcorebank.service.Movement;

import com.bcpc.bffcorebank.domain.Account;
import com.bcpc.bffcorebank.domain.Customer;
import com.bcpc.bffcorebank.domain.Movement;
import com.bcpc.bffcorebank.service.Account.IAccountClient;
import com.bcpc.bffcorebank.service.Customer.ICustomerClient;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
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

    public List<Movement> getMovements() {
        return this.movementClient.fetchMovement();
    }
    public List<Movement> getMovementsByNumAccount(String numAccount) {
        return this.movementClient.getMovementsByNumAccount(numAccount);
    }



    public Movement createMovement(Movement movement) {
        Optional<Account> account = this.accountClient.fetchAccountByNumAccount(movement.getNumberAccount());

        if(account.isPresent()){
            Account ac = account.get();
            System.out.println(movement.getInitialAmount());
            Double initialAmount = ac.getInitialAmount();


            movement.setInitialAmount(initialAmount);

            if(movement.getMovementAmount()>0){
                System.out.println(movement.getMovementAmount());
                //Deposito
                if(movement.getMovementAmount() == 0){
                    return new Movement();
                }
                ac.setInitialAmount(ac.getInitialAmount()+movement.getMovementAmount());

            } else if (movement.getMovementAmount()<0) {
                //Retiro
                if(ac.getInitialAmount() == 0 || Math.abs(movement.getMovementAmount()) > ac.getInitialAmount() ){
                    return new Movement();
                }
                ac.setInitialAmount(ac.getInitialAmount()+movement.getMovementAmount());

            }

            Movement newMovement = this.movementClient.createMovement(movement);

            Account updatedAccount = this.accountClient.updateAccount(ac,ac.getNumberAccount());
            return newMovement;
        }
        return null;

    }


    public Movement updateMovementById(Long movement_id,Movement movement) {
       return this.movementClient.updateMovement(movement,movement_id);

    }


    public Optional<Movement> deleteMovementById(Long movementId) {
        return this.movementClient.deleteMovement(movementId);
    }




}

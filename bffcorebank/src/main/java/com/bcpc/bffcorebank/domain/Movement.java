package com.bcpc.bffcorebank.domain;

import lombok.AccessLevel;
import lombok.AllArgsConstructor;
import lombok.NoArgsConstructor;

@AllArgsConstructor(access = AccessLevel.PUBLIC)
@NoArgsConstructor(access = AccessLevel.PUBLIC)
public class Movement {
    private String numberAccount;
    //    private Date movementAt;
    private String typeMovement;
    private Double initialAmount;
    private Double movementAmount;
    private Integer status;

    public String getNumberAccount() {
        return numberAccount;
    }

    public void setNumberAccount(String numberAccount) {
        this.numberAccount = numberAccount;
    }

    public Integer getStatus() {
        return status;
    }

    public void setStatus(Integer status) {
        this.status = status;
    }

    public Double getMovementAmount() {
        return movementAmount;
    }

    public void setMovementAmount(Double movementAmount) {
        this.movementAmount = movementAmount;
    }

    public Double getInitialAmount() {
        return initialAmount;
    }

    public void setInitialAmount(Double initialAmount) {
        this.initialAmount = initialAmount;
    }

    public String getTypeMovement() {
        return typeMovement;
    }

    public void setTypeMovement(String typeMovement) {
        this.typeMovement = typeMovement;
    }
}

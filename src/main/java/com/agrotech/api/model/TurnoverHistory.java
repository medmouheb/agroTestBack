package com.agrotech.api.model;

import java.time.LocalDateTime;

public class TurnoverHistory {
    private LocalDateTime createdAt = LocalDateTime.now();
    private String movementType;
    private String movementName;
    private float amount;
    private float oldTurnver;
    private float newTurnver;

    public LocalDateTime getCreatedAt() {
        return createdAt;
    }

    public void setCreatedAt(LocalDateTime createdAt) {
        this.createdAt = createdAt;
    }

    public String getMovementType() {
        return movementType;
    }

    public void setMovementType(String movementType) {
        this.movementType = movementType;
    }

    public String getMovementName() {
        return movementName;
    }

    public void setMovementName(String movementName) {
        this.movementName = movementName;
    }

    public float getAmount() {
        return amount;
    }

    public void setAmount(float amount) {
        this.amount = amount;
    }

    public float getOldTurnver() {
        return oldTurnver;
    }

    public void setOldTurnver(float oldTurnver) {
        this.oldTurnver = oldTurnver;
    }

    public float getNewTurnver() {
        return newTurnver;
    }

    public void setNewTurnver(float newTurnver) {
        this.newTurnver = newTurnver;
    }



}

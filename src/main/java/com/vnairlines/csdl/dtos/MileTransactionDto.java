package com.vnairlines.csdl.dtos;

import java.time.LocalDateTime;
import java.util.UUID;

public class MileTransactionDto {
    private UUID transactionId;
    private UUID flightId;
    private Integer milesChange;
    private String reason;
    private LocalDateTime transactionTime;
    public UUID getTransactionId() {
        return transactionId;
    }
    public void setTransactionId(UUID transactionId) {
        this.transactionId = transactionId;
    }
    public UUID getFlightId() {
        return flightId;
    }
    public void setFlightId(UUID flightId) {
        this.flightId = flightId;
    }
    public Integer getMilesChange() {
        return milesChange;
    }
    public void setMilesChange(Integer milesChange) {
        this.milesChange = milesChange;
    }
    public String getReason() {
        return reason;
    }
    public void setReason(String reason) {
        this.reason = reason;
    }
    public LocalDateTime getTransactionTime() {
        return transactionTime;
    }
    public void setTransactionTime(LocalDateTime transactionTime) {
        this.transactionTime = transactionTime;
    }

    
}

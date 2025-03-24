package com.vnairlines.csdl.dtos;

import java.sql.Timestamp;
import java.util.UUID;

public class SeatInventoryDto {

    private UUID seatId;
    private UUID flightId;
    private UUID cabinId;
    private int seatRow;
    private String seatColumn;
    private String seatType;
    private String status; // 'AVAILABLE', 'BOOKED', 'CHECKED-IN', 'CANCELLED'
    private Timestamp updatedAt;

    // Getters and setters
    public UUID getSeatId() {
        return seatId;
    }

    public void setSeatId(UUID seatId) {
        this.seatId = seatId;
    }

    public UUID getFlightId() {
        return flightId;
    }

    public void setFlightId(UUID flightId) {
        this.flightId = flightId;
    }

    public UUID getCabinId() {
        return cabinId;
    }

    public void setCabinId(UUID cabinId) {
        this.cabinId = cabinId;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public String getSeatColumn() {
        return seatColumn;
    }

    public void setSeatColumn(String seatColumn) {
        this.seatColumn = seatColumn;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public Timestamp getUpdatedAt() {
        return updatedAt;
    }

    public void setUpdatedAt(Timestamp updatedAt) {
        this.updatedAt = updatedAt;
    }
}

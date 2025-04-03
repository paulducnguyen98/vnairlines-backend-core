package com.vnairlines.csdl.dtos;

import java.util.UUID;

public class SeatAssignment {

    private UUID passengerId;
    private UUID seatId;

    public SeatAssignment() {
        super();
    }

    public SeatAssignment(UUID passengerId, UUID seatId) {
        super();
        this.passengerId = passengerId;
        this.seatId = seatId;
    }

    public UUID getPassengerId() {
        return passengerId;
    }

    public void setPassengerId(UUID passengerId) {
        this.passengerId = passengerId;
    }

    public UUID getSeatId() {
        return seatId;
    }

    public void setSeatId(UUID seatId) {
        this.seatId = seatId;
    }
}

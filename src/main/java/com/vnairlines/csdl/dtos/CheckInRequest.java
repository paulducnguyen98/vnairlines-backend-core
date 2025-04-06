package com.vnairlines.csdl.dtos;
import java.util.UUID;

public class CheckInRequest {
    private UUID ticketId;
    private String seatNumber;

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public String getSeatNumber() {
        return seatNumber;
    }

    public void setSeatNumber(String seatNumber) {
        this.seatNumber = seatNumber;
    }
}

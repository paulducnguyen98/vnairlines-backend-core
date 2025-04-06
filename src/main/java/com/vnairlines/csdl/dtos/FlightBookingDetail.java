package com.vnairlines.csdl.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public class FlightBookingDetail {

    private UUID flightId;
    private String ticketClass;
    private BigDecimal totalPrice;
    public UUID getFlightId() {
        return flightId;
    }
    public void setFlightId(UUID flightId) {
        this.flightId = flightId;
    }
    public String getTicketClass() {
        return ticketClass;
    }
    public void setTicketClass(String ticketClass) {
        this.ticketClass = ticketClass;
    }
    public BigDecimal getTotalPrice() {
        return totalPrice;
    }
    public void setTotalPrice(BigDecimal totalPrice) {
        this.totalPrice = totalPrice;
    }

    
}

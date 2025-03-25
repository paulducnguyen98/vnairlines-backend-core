package com.vnairlines.csdl.dtos;

import java.math.BigDecimal;

public class TicketPriceDto {
    private String ticketClass;
    private BigDecimal ticketPrice;

    public TicketPriceDto(String ticketClass, BigDecimal ticketPrice) {
        this.ticketClass = ticketClass;
        this.ticketPrice = ticketPrice;
    }

    public String getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(String ticketClass) {
        this.ticketClass = ticketClass;
    }

    public BigDecimal getTicketPrice() {
        return ticketPrice;
    }

    public void setTicketPrice(BigDecimal ticketPrice) {
        this.ticketPrice = ticketPrice;
    }

}
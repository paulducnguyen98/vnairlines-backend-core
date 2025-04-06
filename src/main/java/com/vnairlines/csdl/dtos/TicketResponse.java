package com.vnairlines.csdl.dtos;

import java.math.BigDecimal;
import java.util.UUID;

public class TicketResponse {

    private UUID ticketId;
    private String ticketNumber;
    private String ticketClass;
    private String status;
    private BigDecimal price;
    private UUID seatId;
    private String seatColumn;
    private int seatRow;
    private String seatType;

    public TicketResponse() {
        super();
    }

    public TicketResponse(UUID ticketId, String ticketNumber, String ticketClass, String status, BigDecimal price,
            UUID seatId, String seatColumn, int seatRow, String seatType) {
        super();
        this.ticketId = ticketId;
        this.ticketNumber = ticketNumber;
        this.ticketClass = ticketClass;
        this.status = status;
        this.price = price;
        this.seatId = seatId;
        this.seatColumn = seatColumn;
        this.seatRow = seatRow;
        this.seatType = seatType;
    }

    public UUID getTicketId() {
        return ticketId;
    }

    public void setTicketId(UUID ticketId) {
        this.ticketId = ticketId;
    }

    public String getTicketNumber() {
        return ticketNumber;
    }

    public void setTicketNumber(String ticketNumber) {
        this.ticketNumber = ticketNumber;
    }

    public String getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(String ticketClass) {
        this.ticketClass = ticketClass;
    }

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }

    public BigDecimal getPrice() {
        return price;
    }

    public void setPrice(BigDecimal price) {
        this.price = price;
    }

    public UUID getSeatId() {
        return seatId;
    }

    public void setSeatId(UUID seatId) {
        this.seatId = seatId;
    }

    public String getSeatColumn() {
        return seatColumn;
    }

    public void setSeatColumn(String seatColumn) {
        this.seatColumn = seatColumn;
    }

    public int getSeatRow() {
        return seatRow;
    }

    public void setSeatRow(int seatRow) {
        this.seatRow = seatRow;
    }

    public String getSeatType() {
        return seatType;
    }

    public void setSeatType(String seatType) {
        this.seatType = seatType;
    }

}

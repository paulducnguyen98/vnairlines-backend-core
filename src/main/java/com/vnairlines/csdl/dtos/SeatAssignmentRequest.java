package com.vnairlines.csdl.dtos;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class SeatAssignmentRequest {
    private UUID bookingId;
    private List<SeatAssignment> assignments = new ArrayList<SeatAssignment>();
    private String ticketClass;

    public SeatAssignmentRequest(UUID bookingId, List<SeatAssignment> assignments, String ticketClass) {
        super();
        this.bookingId = bookingId;
        this.assignments = assignments;
        this.ticketClass = ticketClass;
    }

    public SeatAssignmentRequest() {
        super();
    }

    public UUID getBookingId() {
        return bookingId;
    }

    public void setBookingId(UUID bookingId) {
        this.bookingId = bookingId;
    }

    public List<SeatAssignment> getAssignments() {
        return assignments;
    }

    public void setAssignments(List<SeatAssignment> assignments) {
        this.assignments = assignments;
    }

    public String getTicketClass() {
        return ticketClass;
    }

    public void setTicketClass(String ticketClass) {
        this.ticketClass = ticketClass;
    }

}

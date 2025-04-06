package com.vnairlines.csdl.services;

import java.util.List;
import java.util.UUID;

import com.vnairlines.csdl.dtos.TicketDto;

public interface TicketService {
    TicketDto getTicketById(UUID ticketId);

    List<TicketDto> getTicketsByBookingId(UUID bookingId);

    void createTicket(TicketDto ticketDto);

    void updateTicket(UUID ticketId, TicketDto ticketDto);

    void deleteTicket(UUID ticketId);
}

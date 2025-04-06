package com.vnairlines.csdl.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.vnairlines.csdl.dtos.TicketDto;
import com.vnairlines.csdl.services.TicketService;

@Service
public class TicketServiceImpl implements TicketService {

    private final JdbcTemplate jdbcTemplate;

    public TicketServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<TicketDto> ticketRowMapper = (rs, rowNum) -> {
        TicketDto ticketDTO = new TicketDto();
        ticketDTO.setTicketId(UUID.fromString(rs.getString("ticket_id")));
        ticketDTO.setPassengerId(UUID.fromString(rs.getString("passenger_id")));
        ticketDTO.setFlightId(UUID.fromString(rs.getString("flight_id")));
        ticketDTO.setTicketNumber(rs.getString("ticket_number"));
        ticketDTO.setTicketClass(rs.getString("ticket_class"));
        ticketDTO.setPrice(rs.getBigDecimal("price"));
        ticketDTO.setStatus(rs.getString("status"));
        ticketDTO.setSeatId(UUID.fromString(rs.getString("seat_id")));
        ticketDTO.setCreatedAt(rs.getTimestamp("created_at"));
        return ticketDTO;
    };

    @Override
    public TicketDto getTicketById(UUID ticketId) {
        String sql = "SELECT * FROM tickets WHERE ticket_id = ?";
        return jdbcTemplate.queryForObject(sql, ticketRowMapper, ticketId);
    }

    @Override
    public List<TicketDto> getTicketsByBookingId(UUID bookingId) {
        String sql = "SELECT * FROM tickets WHERE booking_id = ?";
        return jdbcTemplate.query(sql, ticketRowMapper, bookingId);
    }

    @Override
    public void createTicket(TicketDto ticketDto) {
        
    }

    @Override
    public void updateTicket(UUID ticketId, TicketDto ticketDto) {
        String sql = """
            UPDATE tickets
            SET ticket_number = ?, ticket_class = ?, price = ?, status = ?
            WHERE ticket_id = ?
        """;
        jdbcTemplate.update(sql, ticketDto.getTicketNumber(), ticketDto.getTicketClass(),
                ticketDto.getPrice(), ticketDto.getStatus(), ticketId);
    }

    @Override
    public void deleteTicket(UUID ticketId) {
        String sql = "DELETE FROM tickets WHERE ticket_id = ?";
        jdbcTemplate.update(sql, ticketId);
    }
}

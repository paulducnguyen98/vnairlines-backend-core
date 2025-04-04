package com.vnairlines.csdl.services.impl;

import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vnairlines.csdl.dtos.CheckInRequest;
import com.vnairlines.csdl.services.CheckInService;

@Service
public class CheckInServiceImpl implements CheckInService {

    private final JdbcTemplate jdbcTemplate;

    public CheckInServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    @Transactional
    public void performCheckIn(CheckInRequest request) {
        UUID ticketId = request.getTicketId();
        String seatNumber = request.getSeatNumber();

        // 1. Validate ticket
        Integer count = jdbcTemplate.queryForObject("""
            SELECT COUNT(*) FROM tickets WHERE ticket_id = ? AND status = 'BOOKED'
        """, Integer.class, ticketId);

        if (count == null || count == 0) {
            throw new IllegalStateException("Invalid or already checked-in ticket");
        }

        // 2. Insert check-in record
        jdbcTemplate.update("""
            INSERT INTO check_in (check_in_id, ticket_id, seat_number, is_checked_in, check_in_time)
            VALUES (?, ?, ?, true, NOW())
        """, UUID.randomUUID(), ticketId, seatNumber);

        // 3. Update ticket status
        jdbcTemplate.update("""
            UPDATE tickets SET status = 'CHECKED-IN' WHERE ticket_id = ?
        """, ticketId);
    }
}

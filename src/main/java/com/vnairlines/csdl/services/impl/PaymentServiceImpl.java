package com.vnairlines.csdl.services.impl;

import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.vnairlines.csdl.dtos.PaymentDto;
import com.vnairlines.csdl.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final JdbcTemplate jdbcTemplate;

    public PaymentServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PaymentDto> paymentRowMapper = (rs, rowNum) -> {
        PaymentDto paymentDTO = new PaymentDto();
        paymentDTO.setPaymentId(UUID.fromString(rs.getString("payment_id")));
        paymentDTO.setBookingId(UUID.fromString(rs.getString("booking_id")));
        paymentDTO.setAmount(rs.getBigDecimal("amount"));
        paymentDTO.setPaymentMethod(rs.getString("payment_method"));
        paymentDTO.setStatus(rs.getString("payment_status"));
        paymentDTO.setTransactionId(rs.getString("transaction_id"));
        paymentDTO.setCreatedAt(rs.getTimestamp("created_at"));
        return paymentDTO;
    };

    @Override
    public PaymentDto getPaymentByBookingId(UUID bookingId) {
        String sql = "SELECT * FROM payments WHERE booking_id = ?";
        return jdbcTemplate.queryForObject(sql, paymentRowMapper, bookingId);
    }

    @Override
    public PaymentDto getPaymentById(UUID paymentId) {
        String sql = "SELECT * FROM payments WHERE payment_id = ?";
        return jdbcTemplate.queryForObject(sql, paymentRowMapper, paymentId);
    }

    @Override
    public void createPayment(PaymentDto paymentDTO) {
        String sql = """
            INSERT INTO payments (payment_id, booking_id, amount, payment_method, payment_status, transaction_id)
            VALUES (?, ?, ?, ?, ?, ?)
        """;
        UUID paymentId = UUID.randomUUID();
        jdbcTemplate.update(sql, paymentId, paymentDTO.getBookingId(), paymentDTO.getAmount(),
                paymentDTO.getPaymentMethod(), paymentDTO.getStatus(), paymentDTO.getTransactionId());
    }

    @Override
    public void updatePayment(UUID paymentId, PaymentDto paymentDTO) {
        String sql = """
            UPDATE payments
            SET amount = ?, payment_method = ?, payment_status = ?, transaction_id = ?
            WHERE payment_id = ?
        """;
        jdbcTemplate.update(sql, paymentDTO.getAmount(), paymentDTO.getPaymentMethod(),
                paymentDTO.getStatus(), paymentDTO.getTransactionId(), paymentId);
    }

    @Override
    public void deletePayment(UUID paymentId) {
        String sql = "DELETE FROM payments WHERE payment_id = ?";
        jdbcTemplate.update(sql, paymentId);
    }
}

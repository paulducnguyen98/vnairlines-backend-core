package com.vnairlines.csdl.services.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.vnairlines.csdl.dtos.PaymentRequest;
import com.vnairlines.csdl.dtos.PaymentResponse;
import com.vnairlines.csdl.services.PaymentService;

@Service
public class PaymentServiceImpl implements PaymentService {

    private final JdbcTemplate jdbcTemplate;

    public PaymentServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<PaymentResponse> paymentRowMapper = (rs, rowNum) -> {
        PaymentResponse paymentResponse = new PaymentResponse();
        paymentResponse.setPaymentId(UUID.fromString(rs.getString("payment_id")));
        paymentResponse.setBookingId(UUID.fromString(rs.getString("booking_id")));
        paymentResponse.setAmount(rs.getBigDecimal("amount"));
        paymentResponse.setPaymentMethod(rs.getString("payment_method"));
        paymentResponse.setStatus(rs.getString("payment_status"));
        paymentResponse.setTransactionId(rs.getString("transaction_id"));
        paymentResponse.setCreatedAt(rs.getTimestamp("created_at"));
        return paymentResponse;
    };

    @Override
    public PaymentResponse getPaymentByBookingId(UUID bookingId) {
        String sql = "SELECT * FROM payments WHERE booking_id = ?";
        return jdbcTemplate.queryForObject(sql, paymentRowMapper, bookingId);
    }

    @Override
    public PaymentResponse getPaymentById(UUID paymentId) {
        String sql = "SELECT * FROM payments WHERE payment_id = ?";
        return jdbcTemplate.queryForObject(sql, paymentRowMapper, paymentId);
    }

    @Override
    public void createPayment(PaymentRequest paymentDTO) {

        UUID paymentId = UUID.randomUUID();
        String transactionId = UUID.randomUUID().toString();
        LocalDateTime now = LocalDateTime.now();

        if (paymentDTO.getBookingId() != null) {
            String sql = """
                        INSERT INTO payments (
                            payment_id, trip_reference_id, amount, payment_method,
                            status, transaction_id, created_at
                        ) VALUES (?, ?, ?, ?::payment_method_type, ?::payment_status_type, ?, ?)
                    """;
            BigDecimal bookingTotal = jdbcTemplate.queryForObject(
                    "SELECT total_price FROM bookings WHERE booking_id = ?", BigDecimal.class,
                    paymentDTO.getBookingId());

            if (bookingTotal == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Booking not found.");
            }

            if (paymentDTO.getAmount() == null || paymentDTO.getAmount().compareTo(bookingTotal) != 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Payment amount must match booking total: " + bookingTotal);
            }

            jdbcTemplate.update(sql, paymentId, paymentDTO.getTripReferenceId(), paymentDTO.getAmount(),
                    paymentDTO.getPaymentMethod(), paymentDTO.getStatus(), transactionId, LocalDateTime.now());

            if ("COMPLETED".equalsIgnoreCase(paymentDTO.getStatus())) {
                jdbcTemplate.update("""
                            UPDATE bookings SET status = 'COMPLETED'
                            WHERE trip_reference_id = ?
                        """, paymentDTO.getTripReferenceId());
            }
        } else if (paymentDTO.getTripReferenceId() != null) {
            BigDecimal tripTotal = jdbcTemplate.queryForObject(
                    "SELECT SUM(total_price) FROM bookings WHERE trip_reference_id = ?", BigDecimal.class,
                    paymentDTO.getTripReferenceId());

            if (tripTotal == null) {
                throw new ResponseStatusException(HttpStatus.NOT_FOUND, "Trip not found.");
            }

            if (paymentDTO.getAmount() == null || paymentDTO.getAmount().compareTo(tripTotal) != 0) {
                throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                        "Payment amount must match total trip cost: " + tripTotal);
            }

            String sql = """
                        INSERT INTO payments (
                            payment_id, trip_reference_id, amount, payment_method,
                            status, transaction_id, created_at
                        ) VALUES (?, ?, ?, ?::payment_method_type, ?::payment_status_type, ?, ?)
                    """;

            jdbcTemplate.update(sql, paymentId, paymentDTO.getTripReferenceId(), paymentDTO.getAmount(),
                    paymentDTO.getPaymentMethod(), paymentDTO.getStatus(), transactionId, now);

            if ("COMPLETED".equalsIgnoreCase(paymentDTO.getStatus())) {
                jdbcTemplate.update("""
                            UPDATE bookings SET status = 'COMPLETED'
                            WHERE trip_reference_id = ?
                        """, paymentDTO.getTripReferenceId());
            }

        } else {
            throw new ResponseStatusException(HttpStatus.BAD_REQUEST,
                    "You must provide either bookingId or tripReferenceId");
        }
    }

}

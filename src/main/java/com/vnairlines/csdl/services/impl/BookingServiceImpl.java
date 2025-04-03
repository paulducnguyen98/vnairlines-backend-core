package com.vnairlines.csdl.services.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vnairlines.csdl.dtos.BookingRequest;
import com.vnairlines.csdl.dtos.BookingResponse;
import com.vnairlines.csdl.dtos.PassengerResponse;
import com.vnairlines.csdl.dtos.PaymentResponse;
import com.vnairlines.csdl.dtos.SeatAssignment;
import com.vnairlines.csdl.dtos.SeatAssignmentRequest;
import com.vnairlines.csdl.models.Booking;
import com.vnairlines.csdl.models.Passenger;
import com.vnairlines.csdl.services.BookingService;
import com.vnairlines.csdl.services.FlightService;

@Service
public class BookingServiceImpl implements BookingService {

    private final JdbcTemplate jdbcTemplate;
    private final FlightService flightService;;

    public BookingServiceImpl(JdbcTemplate jdbcTemplate, FlightService flightService) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        this.flightService = flightService;
    }

    @Override
    public List<BookingResponse> createRoundTripBooking(BookingRequest request) {
        List<BookingResponse> responses = new ArrayList<>();

        // Validate user
        UUID tripReference = request.getFlightIds().size() > 1 ? UUID.randomUUID() : null;

        for (UUID flightId : request.getFlightIds()) {
                flightService.getFlightById(flightId);

                String ticketClass = request.getTicketClasses().get(flightId);
                if (ticketClass == null || ticketClass.isBlank()) {
                    throw new IllegalArgumentException("Ticket class not specified for flight " + flightId);
                }

                Booking booking = new Booking();
                booking.setBookingId(UUID.randomUUID());
                booking.setTripReferenceId(tripReference);
                booking.setUserId(null);
                booking.setBookingCode(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
                booking.setContactFirstName(request.getContactFirstName());
                booking.setContactLastName(request.getContactLastName());
                booking.setContactEmail(request.getContactEmail());
                booking.setContactPhone(request.getContactPhone());
                booking.setTotalPrice(request.getTotalPrice());
                booking.setStatus("PENDING");
                booking.setCreatedAt(LocalDateTime.now());

                String bookingSql = """
                    INSERT INTO bookings (
                        booking_id, user_id, booking_code,
                        contact_first_name, contact_last_name,
                        contact_email, contact_phone, total_price,
                        status, created_at, trip_reference_id
                    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?::booking_status, ?, ?)
                """;

                jdbcTemplate.update(bookingSql,
                    booking.getBookingId(),
                    booking.getUserId(),
                    booking.getBookingCode(),
                    booking.getContactFirstName(),
                    booking.getContactLastName(),
                    booking.getContactEmail(),
                    booking.getContactPhone(),
                    booking.getTotalPrice(),
                    booking.getStatus(),
                    booking.getCreatedAt(),
                    tripReference
                );

                // Create passengers
                List<Passenger> passengers = request.getPassengers().stream().map(p -> {
                    Passenger passenger = new Passenger();
                    passenger.setPassengerId(UUID.randomUUID());
                    passenger.setBookingId(booking.getBookingId());
                    passenger.setFirstName(p.getFirstName());
                    passenger.setLastName(p.getLastName());
                    passenger.setEmail(p.getEmail());
                    passenger.setPhoneNumber(p.getPhoneNumber());
                    passenger.setBirthDate(p.getBirthDate());
                    passenger.setPassportNumber(p.getPassportNumber());
                    passenger.setCitizenId(p.getCitizenId());
                    passenger.setMainContact(p.isMainContact());
                    passenger.setCreatedAt(LocalDateTime.now());
                    return passenger;
                }).collect(Collectors.toList());

                String passengerSql = """
                    INSERT INTO passengers (
                        passenger_id, booking_id, first_name, last_name,
                        email, phone_number, birth_date, passport_number, citizen_id,
                        is_main_contact, created_at
                    ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;

                jdbcTemplate.batchUpdate(passengerSql,
                    passengers,
                    passengers.size(),
                    (ps, passenger) -> {
                        ps.setObject(1, passenger.getPassengerId());
                        ps.setObject(2, passenger.getBookingId());
                        ps.setString(3, passenger.getFirstName());
                        ps.setString(4, passenger.getLastName());
                        ps.setString(5, passenger.getEmail());
                        ps.setString(6, passenger.getPhoneNumber());
                        ps.setObject(7, passenger.getBirthDate());
                        ps.setString(8, passenger.getPassportNumber());
                        ps.setString(9, passenger.getCitizenId());
                        ps.setBoolean(10, passenger.isMainContact());
                        ps.setObject(11, passenger.getCreatedAt());
                    }
                );

                String ticketSql = """
                        INSERT INTO tickets (
                            ticket_id, passenger_id, flight_id, ticket_number,
                            ticket_class, price, status, created_at
                        ) VALUES (?, ?, ?, ?, ?::ticket_class_type, ?, 'BOOKED', ?)
                    """;

                    for (Passenger passenger : passengers) {
                        UUID ticketId = UUID.randomUUID();
                        String ticketNumber = "TK" + ticketId.toString().replace("-", "").substring(0, 8).toUpperCase();

                        BigDecimal price = getFareForFlightAndClass(flightId, ticketClass);

                        jdbcTemplate.update(ticketSql,
                            ticketId,
                            passenger.getPassengerId(),
                            flightId,
                            ticketNumber,
                            ticketClass,
                            price,
                            LocalDateTime.now()
                        );
                    }

                    responses.add(BookingResponse.fromEntity(booking, passengers, ticketClass));
            }

        return responses;
    }

    private BigDecimal getFareForFlightAndClass(UUID flightId, String ticketClass) {
        String sql = """
            SELECT fr.base_fare
            FROM fare_rules fr
            JOIN fare_classes fc ON fr.fare_class_id = fc.fare_class_id
            WHERE fr.flight_id = ? AND fc.ticket_class = ?::ticket_class_type
            LIMIT 1
        """;
        
        return jdbcTemplate.queryForObject(sql, BigDecimal.class, flightId, ticketClass);
    }

    @Override
    public BookingResponse getBookingDetails(UUID bookingId) {
        String bookingSql = """
                SELECT booking_id, trip_reference_id, booking_code, status
                FROM bookings
                WHERE booking_id = ?
            """;

        BookingResponse booking = jdbcTemplate.queryForObject(
                bookingSql,
                (rs, rowNum) -> {
                    BookingResponse dto = new BookingResponse();
                    dto.setBookingId(UUID.fromString(rs.getString("booking_id")));
                    dto.setTripReferenceId((UUID) rs.getObject("trip_reference_id"));
                    dto.setBookingCode(rs.getString("booking_code"));
                    dto.setStatus(rs.getString("status"));
                    return dto;
                },
                bookingId
            );
        UUID tripReferenceId = booking.getTripReferenceId();
            String passengerSql = """
                SELECT passenger_id, first_name, last_name, email, phone_number,
                       birth_date, passport_number, is_main_contact, created_at
                FROM passengers
                WHERE booking_id = ?
            """;

            List<PassengerResponse> passengers = jdbcTemplate.query(
                passengerSql,
                (rs, rowNum) -> {
                    PassengerResponse p = new PassengerResponse();
                    p.setPassengerId(UUID.fromString(rs.getString("passenger_id")));
                    p.setFirstName(rs.getString("first_name"));
                    p.setLastName(rs.getString("last_name"));
                    p.setEmail(rs.getString("email"));
                    p.setPhoneNumber(rs.getString("phone_number"));
                    p.setBirthDate(rs.getDate("birth_date") != null ? rs.getDate("birth_date").toLocalDate() : null);
                    p.setPassportNumber(rs.getString("passport_number"));
                    p.setMainContact(rs.getBoolean("is_main_contact"));
                    p.setCreatedAt(rs.getTimestamp("created_at").toLocalDateTime());
                    return p;
                },
                bookingId
            );
            booking.setPassengers(passengers);

            String paymentSql = """
                SELECT payment_id, amount, payment_method, status,
                       transaction_id, created_at
                FROM payments
                WHERE trip_reference_id = ?
                LIMIT 1
            """;

            List<PaymentResponse> payments = jdbcTemplate.query(
                paymentSql,
                (rs, rowNum) -> {
                    PaymentResponse p = new PaymentResponse();
                    p.setPaymentId(UUID.fromString(rs.getString("payment_id")));
                    p.setAmount(rs.getBigDecimal("amount"));
                    p.setPaymentMethod(rs.getString("payment_method"));
                    p.setStatus(rs.getString("status"));
                    p.setTransactionId(rs.getString("transaction_id"));
                    p.setCreatedAt(rs.getTimestamp("created_at"));
                    return p;
                },
                tripReferenceId
            );

            if (!payments.isEmpty()) {
                booking.setPayment(payments.get(0));
            }

        return booking;
    }

    @Transactional
    public void assignSeatsToPassengers(SeatAssignmentRequest request) {
        UUID bookingId = request.getBookingId();
     // 1. Get flightId from bookingId (assuming all passengers in booking are on same flight)
        UUID flightId = jdbcTemplate.queryForObject("""
            SELECT t.flight_id
            FROM tickets t
            JOIN passengers p ON p.passenger_id = t.passenger_id
            WHERE p.booking_id = ?
            LIMIT 1
        """, UUID.class, bookingId);

        if (flightId == null) {
            throw new IllegalStateException("Cannot determine flight for booking " + bookingId);
        }

        // 2. Loop through assignments
        for (SeatAssignment assignment : request.getAssignments()) {
            UUID passengerId = assignment.getPassengerId();
            UUID seatId = assignment.getSeatId();

            // 3. Check seat availability
            int updated = jdbcTemplate.update("""
                UPDATE seat_inventory
                SET status = 'BOOKED', updated_at = NOW()
                WHERE seat_id = ? AND status = 'AVAILABLE'
            """, seatId);

            if (updated == 0) {
                throw new IllegalStateException("Seat already taken: " + seatId);
            }

            // 4. Update the existing ticket to assign seat_id and (optionally) mark as CHECKED-IN
            jdbcTemplate.update("""
                UPDATE tickets
                SET seat_id = ?, status = 'CHECKED-IN'
                WHERE passenger_id = ? AND flight_id = ?
            """, seatId, passengerId, flightId);
        }
    }

}

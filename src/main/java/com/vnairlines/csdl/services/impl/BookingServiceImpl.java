package com.vnairlines.csdl.services.impl;

import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;
import java.util.Objects;
import java.util.UUID;
import java.util.stream.Collectors;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vnairlines.csdl.dtos.AirportSummary;
import com.vnairlines.csdl.dtos.BookingRequest;
import com.vnairlines.csdl.dtos.BookingResponse;
import com.vnairlines.csdl.dtos.FlightBookingDetail;
import com.vnairlines.csdl.dtos.FlightSummary;
import com.vnairlines.csdl.dtos.PassengerResponse;
import com.vnairlines.csdl.dtos.PaymentRequest;
import com.vnairlines.csdl.dtos.PaymentResponse;
import com.vnairlines.csdl.dtos.SeatAssignment;
import com.vnairlines.csdl.dtos.SeatAssignmentRequest;
import com.vnairlines.csdl.dtos.TicketResponse;
import com.vnairlines.csdl.models.Booking;
import com.vnairlines.csdl.models.Passenger;
import com.vnairlines.csdl.services.BookingService;
import com.vnairlines.csdl.services.FlightService;
import com.vnairlines.csdl.services.MailService;

@Service
public class BookingServiceImpl implements BookingService {

    private final JdbcTemplate jdbcTemplate;
    private final FlightService flightService;
    private final MailService mailService;

    public BookingServiceImpl(JdbcTemplate jdbcTemplate, FlightService flightService, MailService mailService) {
        super();
        this.jdbcTemplate = jdbcTemplate;
        this.flightService = flightService;
        this.mailService = mailService;
    }

    @Override
    public List<BookingResponse> createRoundTripBooking(BookingRequest request) {
        List<BookingResponse> responses = new ArrayList<>();

        UUID tripReferenceId = request.getFlightDetails().size() > 1 ? UUID.randomUUID() : null;

        for (FlightBookingDetail detail : request.getFlightDetails()) {
            UUID flightId = detail.getFlightId();
            String ticketClass = detail.getTicketClass();
            BigDecimal totalPrice = detail.getTotalPrice();

            flightService.getFlightById(flightId);

            if (ticketClass == null || ticketClass.isBlank()) {
                throw new IllegalArgumentException("Ticket class not specified for flight " + flightId);
            }

            Booking booking = new Booking();
            booking.setBookingId(UUID.randomUUID());
            booking.setTripReferenceId(tripReferenceId);
            booking.setUserId(null);
            booking.setBookingCode(UUID.randomUUID().toString().substring(0, 8).toUpperCase());
            booking.setContactFirstName(request.getContactFirstName());
            booking.setContactLastName(request.getContactLastName());
            booking.setContactEmail(request.getContactEmail());
            booking.setContactPhone(request.getContactPhone());
            booking.setTotalPrice(totalPrice);
            booking.setStatus("PENDING");
            booking.setCreatedAt(LocalDateTime.now());

            jdbcTemplate.update("""
                INSERT INTO bookings (
                    booking_id, user_id, booking_code,
                    contact_first_name, contact_last_name,
                    contact_email, contact_phone, total_price,
                    status, created_at, trip_reference_id
                ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?::booking_status, ?, ?)
            """, booking.getBookingId(), booking.getUserId(), booking.getBookingCode(),
                 booking.getContactFirstName(), booking.getContactLastName(), booking.getContactEmail(),
                 booking.getContactPhone(), booking.getTotalPrice(), booking.getStatus(), booking.getCreatedAt(),
                 tripReferenceId);

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

            jdbcTemplate.batchUpdate("""
                INSERT INTO passengers (
                    passenger_id, booking_id, first_name, last_name,
                    email, phone_number, birth_date, passport_number, citizen_id,
                    is_main_contact, created_at
                ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
            """, passengers, passengers.size(), (ps, p) -> {
                ps.setObject(1, p.getPassengerId());
                ps.setObject(2, p.getBookingId());
                ps.setString(3, p.getFirstName());
                ps.setString(4, p.getLastName());
                ps.setString(5, p.getEmail());
                ps.setString(6, p.getPhoneNumber());
                ps.setObject(7, p.getBirthDate());
                ps.setString(8, p.getPassportNumber());
                ps.setString(9, p.getCitizenId());
                ps.setBoolean(10, p.isMainContact());
                ps.setObject(11, p.getCreatedAt());
            });

            for (Passenger passenger : passengers) {
                UUID ticketId = UUID.randomUUID();
                String ticketNumber = "TK" + ticketId.toString().replace("-", "").substring(0, 8).toUpperCase();

                BigDecimal farePrice = getFareForFlightAndClass(flightId, ticketClass); // or use totalPrice / passengers.size()

                jdbcTemplate.update("""
                    INSERT INTO tickets (
                        ticket_id, passenger_id, flight_id, ticket_number,
                        ticket_class, price, status, created_at
                    ) VALUES (?, ?, ?, ?, ?::ticket_class_type, ?, 'BOOKED', ?)
                """, ticketId, passenger.getPassengerId(), flightId, ticketNumber,
                     ticketClass, farePrice, LocalDateTime.now());
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
                    SELECT booking_id, trip_reference_id, booking_code, status, contact_first_name, contact_last_name, contact_email, contact_phone
                    FROM bookings
                    WHERE booking_id = ?
                """;

        BookingResponse booking = jdbcTemplate.queryForObject(bookingSql, (rs, rowNum) -> {
            BookingResponse dto = new BookingResponse();
            dto.setBookingId(UUID.fromString(rs.getString("booking_id")));
            dto.setTripReferenceId((UUID) rs.getObject("trip_reference_id"));
            dto.setBookingCode(rs.getString("booking_code"));
            dto.setStatus(rs.getString("status"));
            dto.setContactFirstName(rs.getString("contact_first_name"));
            dto.setContactLastName(rs.getString("contact_last_name"));
            dto.setContactEmail(rs.getString("contact_email"));
            dto.setContactPhone(rs.getString("contact_phone"));
            return dto;
        }, bookingId);
        String flightSql = """
                    SELECT
                        f.flight_id,
                        f.flight_number,
                        f.departure_time,
                        f.arrival_time,

                        da.airport_code AS dep_code,
                        da.airport_name AS dep_name,
                        da.city AS dep_city,
                        da.country AS dep_country,

                        aa.airport_code AS arr_code,
                        aa.airport_name AS arr_name,
                        aa.city AS arr_city,
                        aa.country AS arr_country

                    FROM tickets t
                    JOIN flights f ON t.flight_id = f.flight_id
                    JOIN airports da ON f.departure_airport_id = da.airport_id
                    JOIN airports aa ON f.arrival_airport_id = aa.airport_id
                    JOIN passengers p ON t.passenger_id = p.passenger_id
                    WHERE p.booking_id = ?
                    LIMIT 1
                """;

        FlightSummary flight = jdbcTemplate.queryForObject(flightSql, (rs, rowNum) -> {
            FlightSummary fs = new FlightSummary();
            fs.setFlightId(UUID.fromString(rs.getString("flight_id")));
            fs.setFlightNumber(rs.getString("flight_number"));
            fs.setDepartureTime(rs.getTimestamp("departure_time").toLocalDateTime());
            fs.setArrivalTime(rs.getTimestamp("arrival_time").toLocalDateTime());

            // Departure airport object
            AirportSummary dep = new AirportSummary();
            dep.setAirportCode(rs.getString("dep_code"));
            dep.setAirportName(rs.getString("dep_name"));
            dep.setCity(rs.getString("dep_city"));
            dep.setCountry(rs.getString("dep_country"));
            fs.setDepartureAirport(dep);

            // Arrival airport object
            AirportSummary arr = new AirportSummary();
            arr.setAirportCode(rs.getString("arr_code"));
            arr.setAirportName(rs.getString("arr_name"));
            arr.setCity(rs.getString("arr_city"));
            arr.setCountry(rs.getString("arr_country"));
            fs.setArrivalAirport(arr);
            return fs;
        }, bookingId);

        booking.setFlight(flight);

        UUID tripReferenceId = booking.getTripReferenceId();
        String passengerSql = """
                    SELECT passenger_id, first_name, last_name, email, phone_number,
                           birth_date, passport_number, is_main_contact, created_at
                    FROM passengers
                    WHERE booking_id = ?
                """;

        List<PassengerResponse> passengers = jdbcTemplate.query(passengerSql, (rs, rowNum) -> {
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
        }, bookingId);

        for (PassengerResponse passenger : passengers) {
            List<TicketResponse> tickets = jdbcTemplate.query("""
                        SELECT t.ticket_id, t.ticket_number, t.ticket_class, t.status, t.price,
                               s.seat_id, s.seat_row, s.seat_column, s.seat_type
                        FROM tickets t
                        LEFT JOIN seat_inventory s ON t.seat_id = s.seat_id
                        WHERE t.passenger_id = ?
                    """, (rs, rowNum) -> {
                TicketResponse t = new TicketResponse();
                t.setTicketId(UUID.fromString(rs.getString("ticket_id")));
                t.setTicketNumber(rs.getString("ticket_number"));
                t.setTicketClass(rs.getString("ticket_class"));
                t.setStatus(rs.getString("status"));
                t.setPrice(rs.getBigDecimal("price"));
                t.setSeatId((UUID) rs.getObject("seat_id"));
                t.setSeatRow(rs.getInt("seat_row"));
                t.setSeatColumn(rs.getString("seat_column"));
                t.setSeatType(rs.getString("seat_type"));
                return t;
            }, passenger.getPassengerId());

            passenger.setTickets(tickets);
        }

        booking.setPassengers(passengers);
        if (!passengers.isEmpty() && !passengers.get(0).getTickets().isEmpty()) {
            // Set ticketClass from first available ticket
            String ticketClass = passengers.get(0).getTickets().get(0).getTicketClass();
            booking.setTicketClass(ticketClass);
        }
        
        BigDecimal totalPrice = passengers.stream()
                .flatMap(p -> p.getTickets().stream())
                .map(TicketResponse::getPrice)
                .filter(Objects::nonNull)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

            booking.setPrice(totalPrice);

        String paymentSql = """
                    SELECT payment_id, amount, payment_method, status,
                           transaction_id, created_at
                    FROM payments
                    WHERE trip_reference_id = ?
                    LIMIT 1
                """;

        List<PaymentResponse> payments = jdbcTemplate.query(paymentSql, (rs, rowNum) -> {
            PaymentResponse p = new PaymentResponse();
            p.setPaymentId(UUID.fromString(rs.getString("payment_id")));
            p.setAmount(rs.getBigDecimal("amount"));
            p.setPaymentMethod(rs.getString("payment_method"));
            p.setStatus(rs.getString("status"));
            p.setTransactionId(rs.getString("transaction_id"));
            p.setCreatedAt(rs.getTimestamp("created_at"));
            return p;
        }, tripReferenceId);

        if (!payments.isEmpty()) {
            booking.setPayment(payments.get(0));
        }

        return booking;
    }

    @Transactional
    public void assignSeatsToPassengers(SeatAssignmentRequest request) {
        UUID bookingId = request.getBookingId();
        // 1. Get flightId from bookingId (assuming all passengers in booking are on
        // same flight)
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

            // 4. Update the existing ticket to assign seat_id and (optionally) mark as
            // CHECKED-IN
            jdbcTemplate.update("""
                        UPDATE tickets
                        SET seat_id = ?, status = 'CHECKED-IN'
                        WHERE passenger_id = ? AND flight_id = ?
                    """, seatId, passengerId, flightId);
        }
    }

    @Transactional
    @Override
    public BookingResponse findBookingByCodeAndEmail(String bookingCode, String email) {
        String sql = """
                    SELECT booking_id
                    FROM bookings
                    WHERE booking_code = ? AND LOWER(contact_email) = LOWER(?)
                """;

        List<UUID> ids = jdbcTemplate.query(sql, (rs, rowNum) -> UUID.fromString(rs.getString("booking_id")),
                bookingCode, email);

        if (ids.isEmpty()) {
            return null;
        }
        return getBookingDetails(ids.get(0));
    }

    @Override
    public void sendConfirmationEmailsFromPaymentRequest(PaymentRequest request) {
        if (request.getTripReferenceId() != null) {
            // Send for all bookings under the trip
            List<Map<String, Object>> bookings = jdbcTemplate.queryForList("""
                        SELECT booking_code, contact_email
                        FROM bookings
                        WHERE trip_reference_id = ?
                    """, request.getTripReferenceId());

            for (Map<String, Object> row : bookings) {
                String email = (String) row.get("contact_email");
                String code = (String) row.get("booking_code");

                if (email != null) {
                    mailService.sendBookingConfirmation(email, code);
                }
            }

        } else if (request.getBookingId() != null) {
            // Send for one booking
            Map<String, Object> result = jdbcTemplate.queryForMap("""
                        SELECT booking_code, contact_email
                        FROM bookings
                        WHERE booking_id = ?
                    """, request.getBookingId());

            String email = (String) result.get("contact_email");
            String code = (String) result.get("booking_code");

            if (email != null) {
                mailService.sendBookingConfirmation(email, code);
            }

        } else {
            throw new IllegalArgumentException("Either bookingId or tripReferenceId must be provided");
        }
    }

}

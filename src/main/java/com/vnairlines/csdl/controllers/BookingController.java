package com.vnairlines.csdl.controllers;

import java.math.BigDecimal;
import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vnairlines.csdl.dtos.BookingRequest;
import com.vnairlines.csdl.dtos.BookingResponse;
import com.vnairlines.csdl.dtos.PaymentRequest;
import com.vnairlines.csdl.dtos.TicketDto;
import com.vnairlines.csdl.dtos.TripBookingResponse;
import com.vnairlines.csdl.services.BookingService;
import com.vnairlines.csdl.services.PaymentService;
import com.vnairlines.csdl.services.TicketService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    private final BookingService bookingService;
    private final TicketService ticketService;
    private final PaymentService paymentService;

    public BookingController(BookingService bookingService, TicketService ticketService, PaymentService paymentService) {
        this.bookingService = bookingService;
        this.ticketService = ticketService;
        this.paymentService = paymentService;
    }

//    // Get booking details with tickets and payments
    @GetMapping("/{id}/details")
    public ResponseEntity<BookingResponse> getBookingDetails(@PathVariable UUID id) {
        BookingResponse bookingDetails = bookingService.getBookingDetails(id);
        return ResponseEntity.ok(bookingDetails);
    }

    @GetMapping("/{id}/tickets")
    public ResponseEntity<List<TicketDto>> getTicketsByBookingId(@PathVariable UUID id) {
        List<TicketDto> tickets = ticketService.getTicketsByBookingId(id);
        return ResponseEntity.ok(tickets);
    }

    @PostMapping
    public ResponseEntity<TripBookingResponse> createBooking(@RequestBody BookingRequest request) {
        List<BookingResponse> response = bookingService.createRoundTripBooking(request);

        BigDecimal totalPrice = response.stream()
                .map(b -> b.getPrice() != null ? b.getPrice() : BigDecimal.ZERO)
                .reduce(BigDecimal.ZERO, BigDecimal::add);

        TripBookingResponse tripResponse = new TripBookingResponse(response, totalPrice.doubleValue());

        return ResponseEntity.status(HttpStatus.CREATED).body(tripResponse);
    }
    @PostMapping("/payment")
    public ResponseEntity<Void> createPayment(@RequestBody PaymentRequest paymentDTO) {
        paymentService.createPayment(paymentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    @PutMapping("/tickets/{ticketId}")
    public ResponseEntity<Void> updateTicket(@PathVariable UUID ticketId, @RequestBody TicketDto ticketDTO) {
        ticketService.updateTicket(ticketId, ticketDTO);
        return ResponseEntity.noContent().build();
    }
}

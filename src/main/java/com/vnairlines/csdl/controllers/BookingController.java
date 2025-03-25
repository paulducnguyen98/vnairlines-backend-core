package com.vnairlines.csdl.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vnairlines.csdl.dtos.PaymentDto;
import com.vnairlines.csdl.dtos.TicketDto;
import com.vnairlines.csdl.services.PaymentService;
import com.vnairlines.csdl.services.TicketService;

@RestController
@RequestMapping("/api/bookings")
public class BookingController {

    //private final BookingService bookingService;
    private final TicketService ticketService;
    private final PaymentService paymentService;

    public BookingController(TicketService ticketService, PaymentService paymentService) {
        //this.bookingService = bookingService;
        this.ticketService = ticketService;
        this.paymentService = paymentService;
    }

//    // Get booking details with tickets and payments
//    @GetMapping("/{id}/details")
//    public ResponseEntity<BookingDto> getBookingDetails(@PathVariable UUID id) {
//        BookingDto bookingDTO = bookingService.getBookingDetails(id);
//        return ResponseEntity.ok(bookingDTO);
//    }

    // Get all tickets for a booking
    @GetMapping("/{id}/tickets")
    public ResponseEntity<List<TicketDto>> getTicketsByBookingId(@PathVariable UUID id) {
        List<TicketDto> tickets = ticketService.getTicketsByBookingId(id);
        return ResponseEntity.ok(tickets);
    }

    // Get payment details for a booking
    @GetMapping("/{id}/payment")
    public ResponseEntity<PaymentDto> getPaymentByBookingId(@PathVariable UUID id) {
        PaymentDto paymentDTO = paymentService.getPaymentByBookingId(id);
        return ResponseEntity.ok(paymentDTO);
    }

//    // Create new ticket
//    @PostMapping("/{id}/tickets")
//    public ResponseEntity<Void> createTicket(@PathVariable UUID id, @RequestBody TicketDto ticketDTO) {
//        ticketDTO.setBookingId(id);
//        ticketService.createTicket(ticketDTO);
//        return ResponseEntity.status(HttpStatus.CREATED).build();
//    }

    // Create new payment
    @PostMapping("/{id}/payment")
    public ResponseEntity<Void> createPayment(@PathVariable UUID id, @RequestBody PaymentDto paymentDTO) {
        paymentDTO.setBookingId(id);
        paymentService.createPayment(paymentDTO);
        return ResponseEntity.status(HttpStatus.CREATED).build();
    }

    // Update ticket status
    @PutMapping("/tickets/{ticketId}")
    public ResponseEntity<Void> updateTicket(@PathVariable UUID ticketId, @RequestBody TicketDto ticketDTO) {
        ticketService.updateTicket(ticketId, ticketDTO);
        return ResponseEntity.noContent().build();
    }

    // Update payment status
    @PutMapping("/payments/{paymentId}")
    public ResponseEntity<Void> updatePayment(@PathVariable UUID paymentId, @RequestBody PaymentDto paymentDTO) {
        paymentService.updatePayment(paymentId, paymentDTO);
        return ResponseEntity.noContent().build();
    }

    // Delete ticket
    @DeleteMapping("/tickets/{ticketId}")
    public ResponseEntity<Void> deleteTicket(@PathVariable UUID ticketId) {
        ticketService.deleteTicket(ticketId);
        return ResponseEntity.noContent().build();
    }

    // Delete payment
    @DeleteMapping("/payments/{paymentId}")
    public ResponseEntity<Void> deletePayment(@PathVariable UUID paymentId) {
        paymentService.deletePayment(paymentId);
        return ResponseEntity.noContent().build();
    }
}

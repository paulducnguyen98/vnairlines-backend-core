package com.vnairlines.csdl.controllers;

import java.util.List;
import java.util.UUID;

import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vnairlines.csdl.dtos.SeatAssignmentRequest;
import com.vnairlines.csdl.models.SeatLayout;
import com.vnairlines.csdl.services.BookingService;
import com.vnairlines.csdl.services.SeatLayoutService;

@RestController
@RequestMapping("/api/seat-layouts")
public class SeatLayoutController {

    private final SeatLayoutService seatLayoutService;
    private final BookingService bookingService;

    public SeatLayoutController(SeatLayoutService seatLayoutService, BookingService bookingService) {
        this.seatLayoutService = seatLayoutService;
        this.bookingService  = bookingService;
    }

    @GetMapping
    public List<SeatLayout> getAllLayouts() {
        return seatLayoutService.getAllLayouts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<SeatLayout> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(seatLayoutService.getLayoutById(id));
    }

    @GetMapping("/name/{name}")
    public ResponseEntity<SeatLayout> getByName(@PathVariable String name) {
        return ResponseEntity.ok(seatLayoutService.getLayoutByName(name));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody SeatLayout layout) {
        seatLayoutService.createLayout(layout);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody SeatLayout layout) {
        seatLayoutService.updateLayout(id, layout);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        seatLayoutService.deleteLayout(id);
        return ResponseEntity.noContent().build();
    }

//    @PostMapping("/seats/assign")
//    public ResponseEntity<?> assignSeats(@RequestBody SeatAssignmentRequest request) {
//        bookingService.assignSeatsToPassengers(request);
//        return ResponseEntity.ok("Seats assigned and tickets created successfully.");
//    }
}
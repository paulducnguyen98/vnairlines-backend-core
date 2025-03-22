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

import com.vnairlines.csdl.models.Flight;
import com.vnairlines.csdl.services.FlightService;

@RestController
@RequestMapping("/api/flights")
public class FlightController {

    private final FlightService flightService;

    public FlightController(FlightService flightService) {
        this.flightService = flightService;
    }

    @GetMapping
    public List<Flight> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Flight> getFlightById(@PathVariable UUID id) {
        return ResponseEntity.ok(flightService.getFlightById(id));
    }

    @GetMapping("/number/{number}")
    public ResponseEntity<Flight> getFlightByNumber(@PathVariable String number) {
        return ResponseEntity.ok(flightService.getFlightByNumber(number));
    }

    @PostMapping
    public ResponseEntity<Void> createFlight(@RequestBody Flight flight) {
        flightService.createFlight(flight);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> updateFlight(@PathVariable UUID id, @RequestBody Flight flight) {
        flightService.updateFlight(id, flight);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> deleteFlight(@PathVariable UUID id) {
        flightService.deleteFlight(id);
        return ResponseEntity.noContent().build();
    }
}
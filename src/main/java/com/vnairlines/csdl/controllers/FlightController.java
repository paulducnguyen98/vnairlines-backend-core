package com.vnairlines.csdl.controllers;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.format.annotation.DateTimeFormat;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vnairlines.csdl.dtos.FlightDetailDto;
import com.vnairlines.csdl.dtos.SeatInventoryDto;
import com.vnairlines.csdl.enums.TicketClassType;
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
    public List<FlightDetailDto> getAllFlights() {
        return flightService.getAllFlights();
    }

    @GetMapping("/{id}")
    public ResponseEntity<FlightDetailDto> getFlightById(@PathVariable UUID id) {
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

    @GetMapping("/{id}/seats")
    public ResponseEntity<List<SeatInventoryDto>> getSeatInventoryByFlightId(@PathVariable UUID id) {
        List<SeatInventoryDto> seatInventory = flightService.getSeatInventoryByFlightId(id);
        return ResponseEntity.ok(seatInventory);
    }

    @GetMapping("/search")
    public ResponseEntity<List<FlightDetailDto>> searchFlights(@RequestParam String departureAirportCode,
            @RequestParam String arrrivalAirportCode,
            @RequestParam @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate departureDate,
            @RequestParam(required = false) @DateTimeFormat(iso = DateTimeFormat.ISO.DATE) LocalDate arrrivalDate,
            @RequestParam(required = false) TicketClassType ticketClass) {
        List<FlightDetailDto> flights = flightService.searchFlights(departureAirportCode, arrrivalAirportCode,
                departureDate, arrrivalDate, ticketClass);
        return ResponseEntity.ok(flights);
    }
}
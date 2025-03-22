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

import com.vnairlines.csdl.models.Airline;
import com.vnairlines.csdl.services.AirlineService;

@RestController
@RequestMapping("/api/airlines")
public class AirlineController {

    private final AirlineService airlineService;

    public AirlineController(AirlineService airlineService) {
        this.airlineService = airlineService;
    }

    @GetMapping
    public List<Airline> getAll() {
        return airlineService.getAllAirlines();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Airline> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(airlineService.getAirlineById(id));
    }

    @GetMapping("/code/{code}")
    public ResponseEntity<Airline> getByCode(@PathVariable String code) {
        return ResponseEntity.ok(airlineService.getAirlineByCode(code));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Airline airline) {
        airlineService.createAirline(airline);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody Airline airline) {
        airlineService.updateAirline(id, airline);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        airlineService.deleteAirline(id);
        return ResponseEntity.noContent().build();
    }
}
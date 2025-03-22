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

import com.vnairlines.csdl.models.Aircraft;
import com.vnairlines.csdl.services.AircraftService;

@RestController
@RequestMapping("/api/aircrafts")
public class AircraftController {

    private final AircraftService aircraftService;

    public AircraftController(AircraftService aircraftService) {
        this.aircraftService = aircraftService;
    }

    @GetMapping
    public List<Aircraft> getAll() {
        return aircraftService.getAllAircrafts();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Aircraft> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(aircraftService.getAircraftById(id));
    }

    @GetMapping("/type/{type}")
    public ResponseEntity<Aircraft> getByType(@PathVariable String type) {
        return ResponseEntity.ok(aircraftService.getAircraftByType(type));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Aircraft aircraft) {
        aircraftService.createAircraft(aircraft);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody Aircraft aircraft) {
        aircraftService.updateAircraft(id, aircraft);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        aircraftService.deleteAircraft(id);
        return ResponseEntity.noContent().build();
    }
}
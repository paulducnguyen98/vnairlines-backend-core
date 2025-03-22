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

import com.vnairlines.csdl.models.Cabin;
import com.vnairlines.csdl.services.CabinService;

@RestController
@RequestMapping("/api/cabins")
public class CabinController {

    private final CabinService cabinService;

    public CabinController(CabinService cabinService) {
        this.cabinService = cabinService;
    }

    @GetMapping
    public List<Cabin> getAllCabins() {
        return cabinService.getAllCabins();
    }

    @GetMapping("/{id}")
    public ResponseEntity<Cabin> getById(@PathVariable UUID id) {
        return ResponseEntity.ok(cabinService.getCabinById(id));
    }

    @GetMapping("/aircraft/{aircraftId}")
    public ResponseEntity<List<Cabin>> getByAircraft(@PathVariable UUID aircraftId) {
        return ResponseEntity.ok(cabinService.getCabinsByAircraftId(aircraftId));
    }

    @PostMapping
    public ResponseEntity<Void> create(@RequestBody Cabin cabin) {
        cabinService.createCabin(cabin);
        return ResponseEntity.ok().build();
    }

    @PutMapping("/{id}")
    public ResponseEntity<Void> update(@PathVariable UUID id, @RequestBody Cabin cabin) {
        cabinService.updateCabin(id, cabin);
        return ResponseEntity.ok().build();
    }

    @DeleteMapping("/{id}")
    public ResponseEntity<Void> delete(@PathVariable UUID id) {
        cabinService.deleteCabin(id);
        return ResponseEntity.noContent().build();
    }
}
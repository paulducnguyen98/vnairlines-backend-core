package com.vnairlines.csdl.services;

import java.util.List;
import java.util.UUID;

import com.vnairlines.csdl.models.Aircraft;

public interface AircraftService {
    List<Aircraft> getAllAircrafts();
    Aircraft getAircraftById(UUID id);
    Aircraft getAircraftByType(String type);
    void createAircraft(Aircraft aircraft);
    void updateAircraft(UUID id, Aircraft aircraft);
    void deleteAircraft(UUID id);
}

package com.vnairlines.csdl.services;

import java.util.List;
import java.util.UUID;

import com.vnairlines.csdl.models.Cabin;

public interface CabinService {
    List<Cabin> getAllCabins();

    List<Cabin> getCabinsByAircraftId(UUID aircraftId);

    Cabin getCabinById(UUID id);

    void createCabin(Cabin cabin);

    void updateCabin(UUID id, Cabin cabin);

    void deleteCabin(UUID id);
}

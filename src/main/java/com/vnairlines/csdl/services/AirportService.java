package com.vnairlines.csdl.services;

import java.util.List;
import java.util.UUID;

import com.vnairlines.csdl.models.Airport;

public interface AirportService {

    List<Airport> getAllAirports();

    Airport getAirportById(UUID id);

    Airport getAirportByCode(String code);

    void createAirport(Airport airport);

    void updateAirport(UUID id, Airport airport);

    void deleteAirport(UUID id);
}

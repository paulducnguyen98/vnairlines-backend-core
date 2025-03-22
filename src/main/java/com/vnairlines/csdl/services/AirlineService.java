package com.vnairlines.csdl.services;

import java.util.List;
import java.util.UUID;

import com.vnairlines.csdl.models.Airline;

public interface AirlineService {
    List<Airline> getAllAirlines();

    Airline getAirlineById(UUID id);

    Airline getAirlineByCode(String code);

    void createAirline(Airline airline);

    void updateAirline(UUID id, Airline airline);

    void deleteAirline(UUID id);
}
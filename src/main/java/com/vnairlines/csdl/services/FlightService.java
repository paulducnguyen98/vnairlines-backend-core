package com.vnairlines.csdl.services;

import java.util.List;
import java.util.UUID;

import com.vnairlines.csdl.dtos.FlightDetailDto;
import com.vnairlines.csdl.models.Flight;

public interface FlightService {
    List<Flight> getAllFlights();

    FlightDetailDto getFlightById(UUID id);

    Flight getFlightByNumber(String number);

    void createFlight(Flight flight);

    void updateFlight(UUID id, Flight flight);

    void deleteFlight(UUID id);
}
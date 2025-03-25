package com.vnairlines.csdl.services;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import com.vnairlines.csdl.dtos.FlightDetailDto;
import com.vnairlines.csdl.dtos.SeatInventoryDto;
import com.vnairlines.csdl.enums.TicketClassType;
import com.vnairlines.csdl.models.Flight;

public interface FlightService {
    List<FlightDetailDto> getAllFlights();

    FlightDetailDto getFlightById(UUID id);

    Flight getFlightByNumber(String number);

    void createFlight(Flight flight);

    void updateFlight(UUID id, Flight flight);

    void deleteFlight(UUID id);

    List<FlightDetailDto> searchFlights(String departureAirportCode, String arrrivalAirportCode,
            LocalDate departureDate, LocalDate returnDate, TicketClassType ticketClass);

    // SEAT
    List<SeatInventoryDto> getSeatInventoryByFlightId(UUID flightId);
}
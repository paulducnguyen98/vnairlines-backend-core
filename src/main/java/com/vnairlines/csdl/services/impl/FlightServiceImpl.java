package com.vnairlines.csdl.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.http.HttpStatus;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;
import org.springframework.web.server.ResponseStatusException;

import com.vnairlines.csdl.models.Flight;
import com.vnairlines.csdl.services.FlightService;

@Service
public class FlightServiceImpl implements FlightService {

    private final JdbcTemplate jdbcTemplate;

    public FlightServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Flight> rowMapper = (rs, rowNum) -> {
        Flight f = new Flight();
        f.setFlightId(UUID.fromString(rs.getString("flight_id")));
        f.setAirlineId(UUID.fromString(rs.getString("airline_id")));
        f.setFlightNumber(rs.getString("flight_number"));
        f.setDepartureAirportId(UUID.fromString(rs.getString("departure_airport_id")));
        f.setArrivalAirportId(UUID.fromString(rs.getString("arrival_airport_id")));
        f.setDepartureTime(rs.getTimestamp("departure_time"));
        f.setArrivalTime(rs.getTimestamp("arrival_time"));
        f.setAircraftId(UUID.fromString(rs.getString("aircraft_id")));
        f.setSeatCapacity(rs.getInt("seat_capacity"));
        f.setBasePrice(rs.getBigDecimal("base_price"));
        return f;
    };

    @Override
    public List<Flight> getAllFlights() {
        return jdbcTemplate.query("SELECT * FROM flights", rowMapper);
    }

    @Override
    public Flight getFlightById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM flights WHERE flight_id = ?", rowMapper, id)
                .stream()
                .findFirst()
                .orElseThrow(() -> new ResponseStatusException(HttpStatus.NOT_FOUND, "Flight not found"));
    }

    @Override
    public Flight getFlightByNumber(String number) {
        return jdbcTemplate.query("SELECT * FROM flights WHERE flight_number = ?", rowMapper, number).stream()
                .findFirst().orElseThrow(() -> new RuntimeException("Flight not found"));
    }

    @Override
    public void createFlight(Flight flight) {
        String sql = """
                    INSERT INTO flights (
                        flight_id, airline_id, flight_number, departure_airport_id, arrival_airport_id,
                        departure_time, arrival_time, aircraft_id, seat_capacity, base_price
                    )
                    VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
                """;
        UUID id = UUID.randomUUID();
        jdbcTemplate.update(sql, id, flight.getAirlineId(), flight.getFlightNumber(), flight.getDepartureAirportId(),
                flight.getArrivalAirportId(), flight.getDepartureTime(), flight.getArrivalTime(),
                flight.getAircraftId(), flight.getSeatCapacity(), flight.getBasePrice());
    }

    @Override
    public void updateFlight(UUID id, Flight flight) {
        String sql = """
                    UPDATE flights
                    SET airline_id = ?, flight_number = ?, departure_airport_id = ?, arrival_airport_id = ?,
                        departure_time = ?, arrival_time = ?, aircraft_id = ?, seat_capacity = ?, base_price = ?
                    WHERE flight_id = ?
                """;
        jdbcTemplate.update(sql, flight.getAirlineId(), flight.getFlightNumber(), flight.getDepartureAirportId(),
                flight.getArrivalAirportId(), flight.getDepartureTime(), flight.getArrivalTime(),
                flight.getAircraftId(), flight.getSeatCapacity(), flight.getBasePrice(), id);
    }

    @Override
    public void deleteFlight(UUID id) {
        jdbcTemplate.update("DELETE FROM flights WHERE flight_id = ?", id.toString());
    }
}
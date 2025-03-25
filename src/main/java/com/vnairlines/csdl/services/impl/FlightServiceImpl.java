package com.vnairlines.csdl.services.impl;

import java.time.LocalDate;
import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.vnairlines.csdl.dtos.AirportDto;
import com.vnairlines.csdl.dtos.FlightDetailDto;
import com.vnairlines.csdl.dtos.SeatInventoryDto;
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

    private final RowMapper<FlightDetailDto> flightDetailRowMapper = (rs, rowNum) -> {
        FlightDetailDto flightDTO = new FlightDetailDto();
        flightDTO.setFlightId(UUID.fromString(rs.getString("flight_id")));
        flightDTO.setFlightNumber(rs.getString("flight_number"));
        flightDTO.setDepartureTime(rs.getTimestamp("departure_time"));
        flightDTO.setArrivalTime(rs.getTimestamp("arrival_time"));
        flightDTO.setSeatCapacity(rs.getInt("seat_capacity"));
        flightDTO.setBasePrice(rs.getBigDecimal("base_price"));

        flightDTO.setDepartureAirport(new AirportDto(UUID.fromString(rs.getString("departure_airport_id")),
                rs.getString("departure_airport_code"), rs.getString("departure_airport_name"),
                rs.getString("departure_city"), rs.getString("departure_country")));

        flightDTO.setArrivalAirport(new AirportDto(UUID.fromString(rs.getString("arrival_airport_id")),
                rs.getString("arrival_airport_code"), rs.getString("arrival_airport_name"),
                rs.getString("arrival_city"), rs.getString("arrival_country")));

        // Thông tin máy bay
        flightDTO.setAircraftType(rs.getString("aircraft_type"));
        flightDTO.setTotalSeats(rs.getInt("total_seats"));
        flightDTO.setRowCount(rs.getInt("row_count"));
        flightDTO.setSeatPerRow(rs.getInt("seat_per_row"));

        return flightDTO;
    };

    @Override
    public List<Flight> getAllFlights() {
        return jdbcTemplate.query("SELECT * FROM flights", rowMapper);
    }

//    @Override
//    public Flight getFlightById(UUID id) {
//        return jdbcTemplate.query("SELECT * FROM flights WHERE flight_id = ?", rowMapper, id).stream().findFirst()
//                .orElseThrow(() -> new RuntimeException("flights not found"));
//    }
    @Override
    public FlightDetailDto getFlightById(UUID id) {
        String sql = """
                    SELECT f.*,
                           da.airport_id as departure_airport_id, da.airport_code AS departure_airport_code, da.airport_name AS departure_airport_name, da.city AS departure_city, da.country AS departure_country,
                           aa.airport_id as arrival_airport_id, aa.airport_code AS arrival_airport_code, aa.airport_name AS arrival_airport_name, aa.city AS arrival_city, aa.country AS arrival_country,
                           ac.aircraft_type, ac.total_seats, ac.row_count, ac.seat_per_row
                    FROM flights f
                    LEFT JOIN airports da ON f.departure_airport_id = da.airport_id
                    LEFT JOIN airports aa ON f.arrival_airport_id = aa.airport_id
                    LEFT JOIN aircrafts ac ON f.aircraft_id = ac.aircraft_id
                    WHERE f.flight_id = ?
                """;

        return jdbcTemplate.query(sql, flightDetailRowMapper, id).stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Flight not found"));
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

    @Override
    public List<SeatInventoryDto> getSeatInventoryByFlightId(UUID flightId) {
        String sql = """
                    SELECT si.*, c.cabin_class, c.start_row, c.end_row
                    FROM seat_inventory si
                    LEFT JOIN cabins c ON si.cabin_id = c.cabin_id
                    WHERE si.flight_id = ?
                """;

        return jdbcTemplate.query(sql, seatInventoryRowMapper, flightId);
    }

    private final RowMapper<SeatInventoryDto> seatInventoryRowMapper = (rs, rowNum) -> {
        SeatInventoryDto seatInventory = new SeatInventoryDto();
        seatInventory.setSeatId(UUID.fromString(rs.getString("seat_id")));
        seatInventory.setFlightId(UUID.fromString(rs.getString("flight_id")));
        seatInventory.setCabinId(UUID.fromString(rs.getString("cabin_id")));
        seatInventory.setSeatRow(rs.getInt("seat_row"));
        seatInventory.setSeatColumn(rs.getString("seat_column"));
        seatInventory.setSeatType(rs.getString("seat_type"));
        seatInventory.setStatus(rs.getString("status"));
        seatInventory.setUpdatedAt(rs.getTimestamp("updated_at"));
        return seatInventory;
    };

    @Override
    public List<FlightDetailDto> searchFlights(String departureAirportCode, String arrrivalAirportCode,
            LocalDate departureDate, LocalDate returnDate) {
        StringBuilder sql = new StringBuilder(
                """
                            SELECT f.*, da.airport_code AS departure_airport_code, da.airport_name AS departure_airport_name, da.city as departure_city, da.country as departure_country,
                                   aa.airport_code AS arrival_airport_code, aa.airport_name AS arrival_airport_name, aa.city as arrival_city, aa.country as arrival_country,
                                   ac.aircraft_type, ac.total_seats, ac.row_count, ac.seat_per_row
                            FROM flights f
                            LEFT JOIN airports da ON f.departure_airport_id = da.airport_id
                            LEFT JOIN airports aa ON f.arrival_airport_id = aa.airport_id
                            LEFT JOIN aircrafts ac ON f.aircraft_id = ac.aircraft_id
                            WHERE f.departure_time::date = ?
                            AND da.airport_code = ? AND aa.airport_code = ?
                        """);

        // Xử lý trường hợp chuyến bay một chiều
        if (returnDate == null) {
            return jdbcTemplate.query(sql.toString(), flightDetailRowMapper, departureDate, departureAirportCode,
                    arrrivalAirportCode);
        }
        // Xử lý trường hợp chuyến bay khứ hồi
        else {
            StringBuilder returnSql = new StringBuilder(
                    """
                                SELECT f.*, da.airport_code AS departure_airport_code, da.airport_name AS departure_airport_name, da.city as departure_city, da.country as departure_country,
                                       aa.airport_code AS arrival_airport_code, aa.airport_name AS arrival_airport_name, aa.city as arrival_city, aa.country as arrival_country,
                                       ac.aircraft_type, ac.total_seats, ac.row_count, ac.seat_per_row
                                FROM flights f
                                LEFT JOIN airports da ON f.departure_airport_id = da.airport_id
                                LEFT JOIN airports aa ON f.arrival_airport_id = aa.airport_id
                                LEFT JOIN aircrafts ac ON f.aircraft_id = ac.aircraft_id
                                WHERE f.departure_time::date = ?
                                AND da.airport_code = ? AND aa.airport_code = ?
                            """);

            List<FlightDetailDto> outboundFlights = jdbcTemplate.query(sql.toString(), flightDetailRowMapper,
                    departureDate, departureAirportCode, arrrivalAirportCode);
            List<FlightDetailDto> returnFlights = jdbcTemplate.query(returnSql.toString(), flightDetailRowMapper,
                    returnDate, departureAirportCode, arrrivalAirportCode);

            outboundFlights.addAll(returnFlights);
            return outboundFlights;
        }
    }

}
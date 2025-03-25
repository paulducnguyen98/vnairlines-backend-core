package com.vnairlines.csdl.services.impl;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.vnairlines.csdl.dtos.AirportDto;
import com.vnairlines.csdl.dtos.FlightDetailDto;
import com.vnairlines.csdl.dtos.SeatInventoryDto;
import com.vnairlines.csdl.dtos.TicketPriceDto;
import com.vnairlines.csdl.enums.TicketClassType;
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

        flightDTO.setAircraftType(rs.getString("aircraft_type"));
        flightDTO.setTotalSeats(rs.getInt("total_seats"));
        flightDTO.setRowCount(rs.getInt("row_count"));
        flightDTO.setSeatPerRow(rs.getInt("seat_per_row"));

        // Lấy danh sách giá vé
        do {
            String ticketClass = rs.getString("ticket_class");
            BigDecimal ticketPrice = rs.getBigDecimal("ticket_price");
            if (ticketClass != null && ticketPrice != null) {
                flightDTO.getTicketPrices().add(new TicketPriceDto(ticketClass, ticketPrice));
            }
        } while (rs.next());

        return flightDTO;
    };

    @Override
    public List<FlightDetailDto> getAllFlights() {
        String sql = """
                    SELECT f.flight_id, f.flight_number, f.departure_time, f.arrival_time, f.seat_capacity, f.base_price,
                           da.airport_id AS departure_airport_id, da.airport_code AS departure_airport_code, da.airport_name AS departure_airport_name, da.city AS departure_city, da.country AS departure_country,
                           aa.airport_id AS arrival_airport_id, aa.airport_code AS arrival_airport_code, aa.airport_name AS arrival_airport_name, aa.city AS arrival_city, aa.country AS arrival_country,
                           ac.aircraft_type, ac.total_seats, ac.row_count, ac.seat_per_row,
                           fc.ticket_class, fr.base_fare AS ticket_price
                    FROM flights f
                    LEFT JOIN airports da ON f.departure_airport_id = da.airport_id
                    LEFT JOIN airports aa ON f.arrival_airport_id = aa.airport_id
                    LEFT JOIN aircrafts ac ON f.aircraft_id = ac.aircraft_id
                    LEFT JOIN fare_rules fr ON f.flight_id = fr.flight_id
                    LEFT JOIN fare_classes fc ON fr.fare_class_id = fc.fare_class_id
                    ORDER BY f.departure_time ASC
                """;

        List<FlightDetailDto> flights = jdbcTemplate.query(sql, (rs) -> {
            Map<UUID, FlightDetailDto> flightMap = new HashMap<>();

            while (rs.next()) {
                UUID flightId = rs.getObject("flight_id", UUID.class);

                // Check if we already added this flight
                FlightDetailDto flight = flightMap.get(flightId);
                if (flight == null) {
                    flight = new FlightDetailDto();
                    flight.setFlightId(flightId);
                    flight.setFlightNumber(rs.getString("flight_number"));
                    flight.setDepartureTime(rs.getTimestamp("departure_time"));
                    flight.setArrivalTime(rs.getTimestamp("arrival_time"));
                    flight.setSeatCapacity(rs.getInt("seat_capacity"));
                    flight.setBasePrice(rs.getBigDecimal("base_price"));

                    // Set departure airport
                    AirportDto departureAirport = new AirportDto();
                    departureAirport.setId(rs.getObject("departure_airport_id", UUID.class));
                    departureAirport.setAirportCode(rs.getString("departure_airport_code"));
                    departureAirport.setAirportName(rs.getString("departure_airport_name"));
                    departureAirport.setCity(rs.getString("departure_city"));
                    departureAirport.setCountry(rs.getString("departure_country"));
                    flight.setDepartureAirport(departureAirport);

                    // Set arrival airport
                    AirportDto arrivalAirport = new AirportDto();
                    arrivalAirport.setId(rs.getObject("arrival_airport_id", UUID.class));
                    arrivalAirport.setAirportCode(rs.getString("arrival_airport_code"));
                    arrivalAirport.setAirportName(rs.getString("arrival_airport_name"));
                    arrivalAirport.setCity(rs.getString("arrival_city"));
                    arrivalAirport.setCountry(rs.getString("arrival_country"));
                    flight.setArrivalAirport(arrivalAirport);

                    // Aircraft details
                    flight.setAircraftType(rs.getString("aircraft_type"));
                    flight.setTotalSeats(rs.getInt("total_seats"));
                    flight.setRowCount(rs.getInt("row_count"));
                    flight.setSeatPerRow(rs.getInt("seat_per_row"));

                    // Initialize ticket prices list
                    flight.setTicketPrices(new ArrayList<>());

                    // Put in map
                    flightMap.put(flightId, flight);
                }

                // Add ticket class & price
                String ticketClass = rs.getString("ticket_class");
                BigDecimal ticketPrice = rs.getBigDecimal("ticket_price");
                if (ticketClass != null && ticketPrice != null) {
                    flight.getTicketPrices().add(new TicketPriceDto(ticketClass, ticketPrice));
                }
            }

            return new ArrayList<>(flightMap.values());
        });

        return flights;
    }

    @Override
    public FlightDetailDto getFlightById(UUID id) {
        String sql = """
                    SELECT f.flight_id, f.flight_number, f.departure_time, f.arrival_time, f.seat_capacity, f.base_price,
                           da.airport_id AS departure_airport_id, da.airport_code AS departure_airport_code, da.airport_name AS departure_airport_name, da.city AS departure_city, da.country AS departure_country,
                           aa.airport_id AS arrival_airport_id, aa.airport_code AS arrival_airport_code, aa.airport_name AS arrival_airport_name, aa.city AS arrival_city, aa.country AS arrival_country,
                           ac.aircraft_type, ac.total_seats, ac.row_count, ac.seat_per_row,
                           fc.ticket_class, fr.base_fare AS ticket_price
                    FROM flights f
                    LEFT JOIN airports da ON f.departure_airport_id = da.airport_id
                    LEFT JOIN airports aa ON f.arrival_airport_id = aa.airport_id
                    LEFT JOIN aircrafts ac ON f.aircraft_id = ac.aircraft_id
                    LEFT JOIN fare_rules fr ON f.flight_id = fr.flight_id
                    LEFT JOIN fare_classes fc ON fr.fare_class_id = fc.fare_class_id
                    WHERE f.flight_id = ?
                    ORDER BY f.departure_time ASC
                """;

        return jdbcTemplate.query(sql, (rs) -> {
            FlightDetailDto flight = null;

            while (rs.next()) {
                if (flight == null) {
                    flight = new FlightDetailDto();
                    flight.setFlightId(rs.getObject("flight_id", UUID.class));
                    flight.setFlightNumber(rs.getString("flight_number"));
                    flight.setDepartureTime(rs.getTimestamp("departure_time"));
                    flight.setArrivalTime(rs.getTimestamp("arrival_time"));
                    flight.setSeatCapacity(rs.getInt("seat_capacity"));
                    flight.setBasePrice(rs.getBigDecimal("base_price"));

                    // Set departure airport
                    AirportDto departureAirport = new AirportDto();
                    departureAirport.setId(rs.getObject("departure_airport_id", UUID.class));
                    departureAirport.setAirportCode(rs.getString("departure_airport_code"));
                    departureAirport.setAirportName(rs.getString("departure_airport_name"));
                    departureAirport.setCity(rs.getString("departure_city"));
                    departureAirport.setCountry(rs.getString("departure_country"));
                    flight.setDepartureAirport(departureAirport);

                    // Set arrival airport
                    AirportDto arrivalAirport = new AirportDto();
                    arrivalAirport.setId(rs.getObject("arrival_airport_id", UUID.class));
                    arrivalAirport.setAirportCode(rs.getString("arrival_airport_code"));
                    arrivalAirport.setAirportName(rs.getString("arrival_airport_name"));
                    arrivalAirport.setCity(rs.getString("arrival_city"));
                    arrivalAirport.setCountry(rs.getString("arrival_country"));
                    flight.setArrivalAirport(arrivalAirport);

                    // Aircraft details
                    flight.setAircraftType(rs.getString("aircraft_type"));
                    flight.setTotalSeats(rs.getInt("total_seats"));
                    flight.setRowCount(rs.getInt("row_count"));
                    flight.setSeatPerRow(rs.getInt("seat_per_row"));

                    // Initialize ticket prices list
                    flight.setTicketPrices(new ArrayList<>());
                }

                // Add ticket class & price
                String ticketClass = rs.getString("ticket_class");
                BigDecimal ticketPrice = rs.getBigDecimal("ticket_price");
                if (ticketClass != null && ticketPrice != null) {
                    flight.getTicketPrices().add(new TicketPriceDto(ticketClass, ticketPrice));
                }
            }

            if (flight == null) {
                throw new RuntimeException("Flight not found");
            }

            return flight;
        }, id);
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
    public List<FlightDetailDto> searchFlights(String departureAirportCode, String arrivalAirportCode,
            LocalDate departureDate, LocalDate returnDate, TicketClassType ticketClassType) {

        StringBuilder sql = new StringBuilder(
                """
                        SELECT f.*,
                               da.airport_code AS departure_airport_code, da.airport_name AS departure_airport_name, da.city as departure_city, da.country as departure_country,
                               aa.airport_code AS arrival_airport_code, aa.airport_name AS arrival_airport_name, aa.city as arrival_city, aa.country as arrival_country,
                               ac.aircraft_type, ac.total_seats, ac.row_count, ac.seat_per_row,
                               fr.base_fare AS ticket_price,
                               fc.ticket_class
                        FROM flights f
                        LEFT JOIN airports da ON f.departure_airport_id = da.airport_id
                        LEFT JOIN airports aa ON f.arrival_airport_id = aa.airport_id
                        LEFT JOIN aircrafts ac ON f.aircraft_id = ac.aircraft_id
                        LEFT JOIN fare_rules fr ON f.flight_id = fr.flight_id
                        LEFT JOIN fare_classes fc ON fr.fare_class_id = fc.fare_class_id
                        WHERE f.departure_time::date = ?
                          AND da.airport_code = ?
                          AND aa.airport_code = ?
                        """);

        List<Object> params = new ArrayList<>(List.of(departureDate, departureAirportCode, arrivalAirportCode));

        if (ticketClassType != null) {
            sql.append(" AND fc.ticket_class = ?::ticket_class_type");
            params.add(ticketClassType.name());
        }

        if (returnDate == null) {
            return jdbcTemplate.query(sql.toString(), flightDetailRowMapper, params.toArray());
        } else {
            StringBuilder returnSql = new StringBuilder(
                    """
                            SELECT f.*,
                                   da.airport_code AS departure_airport_code, da.airport_name AS departure_airport_name, da.city as departure_city, da.country as departure_country,
                                   aa.airport_code AS arrival_airport_code, aa.airport_name AS arrival_airport_name, aa.city as arrival_city, aa.country as arrival_country,
                                   ac.aircraft_type, ac.total_seats, ac.row_count, ac.seat_per_row,
                                   fr.base_fare AS ticket_price,
                                   fc.ticket_class
                            FROM flights f
                            LEFT JOIN airports da ON f.departure_airport_id = da.airport_id
                            LEFT JOIN airports aa ON f.arrival_airport_id = aa.airport_id
                            LEFT JOIN aircrafts ac ON f.aircraft_id = ac.aircraft_id
                            LEFT JOIN fare_rules fr ON f.flight_id = fr.flight_id
                            LEFT JOIN fare_classes fc ON fr.fare_class_id = fc.fare_class_id
                            WHERE f.departure_time::date = ?
                              AND da.airport_code = ?
                              AND aa.airport_code = ?
                            """);

            List<Object> returnParams = new ArrayList<>(List.of(returnDate, arrivalAirportCode, departureAirportCode));

            if (ticketClassType != null) {
                returnSql.append(" AND fc.ticket_class = ?::ticket_class_type");
                returnParams.add(ticketClassType.name());
            }

            List<FlightDetailDto> outboundFlights = jdbcTemplate.query(sql.toString(), flightDetailRowMapper,
                    params.toArray());
            List<FlightDetailDto> returnFlights = jdbcTemplate.query(returnSql.toString(), flightDetailRowMapper,
                    returnParams.toArray());

            outboundFlights.addAll(returnFlights);
            return outboundFlights;
        }
    }

}
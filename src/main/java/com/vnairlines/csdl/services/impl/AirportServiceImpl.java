package com.vnairlines.csdl.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.vnairlines.csdl.models.Airport;
import com.vnairlines.csdl.services.AirportService;

@Service
public class AirportServiceImpl implements AirportService {

    private final JdbcTemplate jdbcTemplate;

    public AirportServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Airport> rowMapper = (rs, rowNum) -> {
        Airport a = new Airport();
        a.setAirportId(UUID.fromString(rs.getString("airport_id")));
        a.setAirportCode(rs.getString("airport_code"));
        a.setAirportName(rs.getString("airport_name"));
        a.setCity(rs.getString("city"));
        a.setCountry(rs.getString("country"));
        return a;
    };

    @Override
    public List<Airport> getAllAirports() {
        return jdbcTemplate.query("SELECT * FROM airports", rowMapper);
    }

    @Override
    public Airport getAirportById(UUID id) {
        String sql = "SELECT * FROM airports WHERE airport_id = ?";
        return jdbcTemplate.query(sql, rowMapper, id).stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Airport not found"));
    }

    @Override
    public Airport getAirportByCode(String code) {
        String sql = "SELECT * FROM airports WHERE airport_code = ?";
        return jdbcTemplate.query(sql, rowMapper, code).stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Airport not found"));
    }

    @Override
    public void createAirport(Airport airport) {
        String sql = """
                    INSERT INTO airports (airport_id, airport_code, airport_name, city, country)
                    VALUES (?, ?, ?, ?, ?)
                """;
        UUID id = UUID.randomUUID();
        jdbcTemplate.update(sql, id, airport.getAirportCode(), airport.getAirportName(), airport.getCity(),
                airport.getCountry());
    }

    @Override
    public void updateAirport(UUID id, Airport airport) {
        String sql = """
                    UPDATE airports
                    SET airport_code = ?, airport_name = ?, city = ?, country = ?
                    WHERE airport_id = ?
                """;
        jdbcTemplate.update(sql, airport.getAirportCode(), airport.getAirportName(), airport.getCity(),
                airport.getCountry(), id);
    }

    @Override
    public void deleteAirport(UUID id) {
        jdbcTemplate.update("DELETE FROM airports WHERE airport_id = ?", id.toString());
    }
}
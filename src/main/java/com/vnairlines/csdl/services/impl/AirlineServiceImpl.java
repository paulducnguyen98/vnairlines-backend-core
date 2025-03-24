package com.vnairlines.csdl.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.vnairlines.csdl.models.Airline;
import com.vnairlines.csdl.services.AirlineService;

@Service
public class AirlineServiceImpl implements AirlineService {
    private final JdbcTemplate jdbcTemplate;

    public AirlineServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Airline> rowMapper = (rs, rowNum) -> {
        Airline a = new Airline();
        a.setAirlineId(UUID.fromString(rs.getString("airline_id")));
        a.setAirlineName(rs.getString("airline_name"));
        a.setAirlineCode(rs.getString("airline_code"));
        return a;
    };

    @Override
    public List<Airline> getAllAirlines() {
        return jdbcTemplate.query("SELECT * FROM airlines", rowMapper);
    }

    @Override
    public Airline getAirlineById(UUID id) {
        String sql = "SELECT * FROM airlines WHERE airline_id = ?";
        return jdbcTemplate.query(sql, rowMapper, id).stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Airline not found"));
    }

    @Override
    public Airline getAirlineByCode(String code) {
        String sql = "SELECT * FROM airlines WHERE airline_code = ?";
        return jdbcTemplate.query(sql, rowMapper, code).stream().findFirst()
                .orElseThrow(() -> new RuntimeException("Airline not found"));
    }

    @Override
    public void createAirline(Airline airline) {
        String sql = """
                    INSERT INTO airlines (airline_id, airline_name, airline_code)
                    VALUES (?, ?, ?)
                """;
        UUID id = UUID.randomUUID();
        jdbcTemplate.update(sql, id, airline.getAirlineName(), airline.getAirlineCode());
    }

    @Override
    public void updateAirline(UUID id, Airline airline) {
        String sql = """
                    UPDATE airlines
                    SET airline_name = ?, airline_code = ?
                    WHERE airline_id = ?
                """;
        jdbcTemplate.update(sql, airline.getAirlineName(), airline.getAirlineCode(), id);
    }

    @Override
    public void deleteAirline(UUID id) {
        jdbcTemplate.update("DELETE FROM airlines WHERE airline_id = ?", id.toString());
    }
}

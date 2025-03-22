package com.vnairlines.csdl.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.vnairlines.csdl.models.Aircraft;
import com.vnairlines.csdl.services.AircraftService;

@Service
public class AircraftServiceImpl implements AircraftService {

    private final JdbcTemplate jdbcTemplate;

    public AircraftServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Aircraft> rowMapper = (rs, rowNum) -> {
        Aircraft a = new Aircraft();
        a.setAircraftId(UUID.fromString(rs.getString("aircraft_id")));
        a.setAircraftType(rs.getString("aircraft_type"));
        a.setTotalSeats(rs.getInt("total_seats"));
        a.setRowCount(rs.getInt("row_count"));
        a.setSeatPerRow(rs.getInt("seat_per_row"));
        return a;
    };

    @Override
    public List<Aircraft> getAllAircrafts() {
        return jdbcTemplate.query("SELECT * FROM aircrafts", rowMapper);
    }

    @Override
    public Aircraft getAircraftById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM aircrafts WHERE aircraft_id = ?", rowMapper, id.toString()).stream()
                .findFirst().orElseThrow(() -> new RuntimeException("Aircraft not found"));
    }

    @Override
    public Aircraft getAircraftByType(String type) {
        return jdbcTemplate.query("SELECT * FROM aircrafts WHERE aircraft_type = ?", rowMapper, type).stream()
                .findFirst().orElseThrow(() -> new RuntimeException("Aircraft not found"));
    }

    @Override
    public void createAircraft(Aircraft aircraft) {
        String sql = """
                    INSERT INTO aircrafts (aircraft_id, aircraft_type, total_seats, row_count, seat_per_row)
                    VALUES (?, ?, ?, ?, ?)
                """;
        UUID id = UUID.randomUUID();
        jdbcTemplate.update(sql, id, aircraft.getAircraftType(), aircraft.getTotalSeats(), aircraft.getRowCount(),
                aircraft.getSeatPerRow());
    }

    @Override
    public void updateAircraft(UUID id, Aircraft aircraft) {
        String sql = """
                    UPDATE aircrafts
                    SET aircraft_type = ?, total_seats = ?, row_count = ?, seat_per_row = ?
                    WHERE aircraft_id = ?
                """;
        jdbcTemplate.update(sql, aircraft.getAircraftType(), aircraft.getTotalSeats(), aircraft.getRowCount(),
                aircraft.getSeatPerRow(), id);
    }

    @Override
    public void deleteAircraft(UUID id) {
        jdbcTemplate.update("DELETE FROM aircrafts WHERE aircraft_id = ?", id.toString());
    }
}
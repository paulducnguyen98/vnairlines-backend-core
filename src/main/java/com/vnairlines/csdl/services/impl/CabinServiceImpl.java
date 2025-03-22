package com.vnairlines.csdl.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.vnairlines.csdl.models.Cabin;
import com.vnairlines.csdl.services.CabinService;

@Service
public class CabinServiceImpl implements CabinService {

    private final JdbcTemplate jdbcTemplate;

    public CabinServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<Cabin> rowMapper = (rs, rowNum) -> {
        Cabin c = new Cabin();
        c.setCabinId(UUID.fromString(rs.getString("cabin_id")));
        c.setAircraftId(UUID.fromString(rs.getString("aircraft_id")));
        c.setCabinClass(rs.getString("cabin_class"));
        c.setStartRow(rs.getInt("start_row"));
        c.setEndRow(rs.getInt("end_row"));
        c.setLayoutId(UUID.fromString(rs.getString("layout_id")));
        c.setCabinCapacity(rs.getInt("cabin_capacity"));
        return c;
    };

    @Override
    public List<Cabin> getAllCabins() {
        return jdbcTemplate.query("SELECT * FROM cabins", rowMapper);
    }

    @Override
    public List<Cabin> getCabinsByAircraftId(UUID aircraftId) {
        return jdbcTemplate.query("SELECT * FROM cabins WHERE aircraft_id = ?", rowMapper, aircraftId.toString());
    }

    @Override
    public Cabin getCabinById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM cabins WHERE cabin_id = ?", rowMapper, id.toString()).stream()
                .findFirst().orElseThrow(() -> new RuntimeException("Cabin not found"));
    }

    @Override
    public void createCabin(Cabin cabin) {
        String sql = """
                    INSERT INTO cabins (cabin_id, aircraft_id, cabin_class, start_row, end_row, layout_id, cabin_capacity)
                    VALUES (?, ?, ?, ?, ?, ?, ?)
                """;
        UUID id = UUID.randomUUID();
        jdbcTemplate.update(sql, id, cabin.getAircraftId(), cabin.getCabinClass(), cabin.getStartRow(),
                cabin.getEndRow(), cabin.getLayoutId(), cabin.getCabinCapacity());
    }

    @Override
    public void updateCabin(UUID id, Cabin cabin) {
        String sql = """
                    UPDATE cabins
                    SET aircraft_id = ?, cabin_class = ?, start_row = ?, end_row = ?, layout_id = ?, cabin_capacity = ?
                    WHERE cabin_id = ?
                """;
        jdbcTemplate.update(sql, cabin.getAircraftId(), cabin.getCabinClass(), cabin.getStartRow(), cabin.getEndRow(),
                cabin.getLayoutId(), cabin.getCabinCapacity(), id);
    }

    @Override
    public void deleteCabin(UUID id) {
        jdbcTemplate.update("DELETE FROM cabins WHERE cabin_id = ?", id.toString());
    }
}
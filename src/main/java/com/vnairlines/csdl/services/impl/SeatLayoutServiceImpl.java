package com.vnairlines.csdl.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Service;

import com.vnairlines.csdl.models.SeatLayout;
import com.vnairlines.csdl.services.SeatLayoutService;

@Service
public class SeatLayoutServiceImpl implements SeatLayoutService {

    private final JdbcTemplate jdbcTemplate;

    public SeatLayoutServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    private final RowMapper<SeatLayout> rowMapper = (rs, rowNum) -> {
        SeatLayout layout = new SeatLayout();
        layout.setLayoutId(UUID.fromString(rs.getString("layout_id")));
        layout.setLayoutName(rs.getString("layout_name"));
        layout.setSeatColumns(rs.getString("seat_columns"));
        return layout;
    };

    @Override
    public List<SeatLayout> getAllLayouts() {
        return jdbcTemplate.query("SELECT * FROM seat_layouts", rowMapper);
    }

    @Override
    public SeatLayout getLayoutById(UUID id) {
        return jdbcTemplate.query("SELECT * FROM seat_layouts WHERE layout_id = ?", rowMapper, id.toString()).stream()
                .findFirst().orElseThrow(() -> new RuntimeException("Layout not found"));
    }

    @Override
    public SeatLayout getLayoutByName(String layoutName) {
        return jdbcTemplate.query("SELECT * FROM seat_layouts WHERE layout_name = ?", rowMapper, layoutName).stream()
                .findFirst().orElseThrow(() -> new RuntimeException("Layout not found"));
    }

    @Override
    public void createLayout(SeatLayout layout) {
        String sql = """
                    INSERT INTO seat_layouts (layout_id, layout_name, seat_columns)
                    VALUES (?, ?, ?)
                """;
        UUID id = UUID.randomUUID();
        jdbcTemplate.update(sql, id, layout.getLayoutName(), layout.getSeatColumns());
    }

    @Override
    public void updateLayout(UUID id, SeatLayout layout) {
        String sql = """
                    UPDATE seat_layouts
                    SET layout_name = ?, seat_columns = ?
                    WHERE layout_id = ?
                """;
        jdbcTemplate.update(sql, layout.getLayoutName(), layout.getSeatColumns(), id);
    }

    @Override
    public void deleteLayout(UUID id) {
        jdbcTemplate.update("DELETE FROM seat_layouts WHERE layout_id = ?", id.toString());
    }
}
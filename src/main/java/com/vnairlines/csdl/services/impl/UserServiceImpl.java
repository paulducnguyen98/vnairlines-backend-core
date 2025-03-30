package com.vnairlines.csdl.services.impl;

import java.util.List;
import java.util.UUID;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.vnairlines.csdl.models.UserDto;
import com.vnairlines.csdl.services.UserService;

@Service
public class UserServiceImpl implements UserService {

    private final JdbcTemplate jdbcTemplate;

    public UserServiceImpl(JdbcTemplate jdbcTemplate) {
        this.jdbcTemplate = jdbcTemplate;
    }

    @Override
    public List<UserDto> getAllUsers() {
        String sql = """
                        SELECT
                        u.user_id, u.first_name, u.last_name, u.email, u.phone_number,
                        u.is_admin, u.is_loyalty_member, u.created_at,
                        ulp.current_tier_id, mt.tier_name
                    FROM users u
                    LEFT JOIN user_loyalty_profiles ulp ON u.user_id = ulp.user_id
                    LEFT JOIN membership_tiers mt ON ulp.current_tier_id = mt.tier_id
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            UserDto user = new UserDto();
            user.setUserId(UUID.fromString(rs.getString("user_id")));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setPhoneNumber(rs.getString("phone_number"));
            user.setAdmin(rs.getBoolean("is_admin"));
            user.setLoyaltyMember(rs.getBoolean("is_loyalty_member"));
            user.setCreatedAt(rs.getTimestamp("created_at"));

            // New: Set tier info if available
            String tierId = rs.getString("current_tier_id");
            if (tierId != null) {
                user.setTierId(tierId);
                user.setTierName(rs.getString("tier_name"));
            }

            return user;
        });
    }
}

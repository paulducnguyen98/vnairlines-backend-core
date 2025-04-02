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
                        u.address, u.identity_number, u.identity_issued_date,
                        u.identity_issued_place, u.gender,
                        u.created_at,
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

            user.setGender(rs.getString("gender"));
            user.setAddress(rs.getString("address"));
            user.setIdentityNumber(rs.getString("identity_number"));
            user.setIdentityIssuedDate(rs.getDate("identity_issued_date"));
            user.setIdentityIssuedPlace(rs.getString("identity_issued_place"));
            user.setCreatedAt(rs.getTimestamp("created_at"));

            String tierId = rs.getString("current_tier_id");
            if (tierId != null) {
                user.setTierId(tierId);
                user.setTierName(rs.getString("tier_name"));
            }

            return user;
        });
    }

    @Override
    public UserDto getUserById(UUID userId) {
        String sql = """
                    SELECT
                        u.user_id, u.first_name, u.last_name, u.email, u.phone_number,
                        u.address, u.identity_number, u.identity_issued_date,
                        u.identity_issued_place, u.gender,
                        u.created_at,
                        ulp.current_tier_id, mt.tier_name
                    FROM users u
                    LEFT JOIN user_loyalty_profiles ulp ON u.user_id = ulp.user_id
                    LEFT JOIN membership_tiers mt ON ulp.current_tier_id = mt.tier_id
                    WHERE u.user_id = ?
                """;

        List<UserDto> result = jdbcTemplate.query(sql, (rs, rowNum) -> {
            UserDto user = new UserDto();
            user.setUserId(UUID.fromString(rs.getString("user_id")));
            user.setFirstName(rs.getString("first_name"));
            user.setLastName(rs.getString("last_name"));
            user.setEmail(rs.getString("email"));
            user.setPhoneNumber(rs.getString("phone_number"));

            user.setGender(rs.getString("gender"));
            user.setAddress(rs.getString("address"));
            user.setIdentityNumber(rs.getString("identity_number"));
            user.setIdentityIssuedDate(rs.getDate("identity_issued_date"));
            user.setIdentityIssuedPlace(rs.getString("identity_issued_place"));
            user.setCreatedAt(rs.getTimestamp("created_at"));

            String tierId = rs.getString("current_tier_id");
            if (tierId != null) {
                user.setTierId(tierId);
                user.setTierName(rs.getString("tier_name"));
            }

            return user;
        }, userId);

        return result.isEmpty() ? null : result.get(0);
    }

    @Override
    public UserDto createUser(UserDto user) {
        String insertUserSql = """
            INSERT INTO users (
                user_id, first_name, last_name, email, phone_number, address,
                identity_number, identity_issued_date, identity_issued_place,
                gender, created_at
            ) VALUES (?, ?, ?, ?, ?, ?, ?, ?, ?, ?, ?)
        """;

        UUID userId = UUID.randomUUID();
        java.sql.Timestamp createdAt = new java.sql.Timestamp(System.currentTimeMillis());
        user.setUserId(userId);
        user.setCreatedAt(createdAt);
        jdbcTemplate.update(insertUserSql,
            userId,
            user.getFirstName(),
            user.getLastName(),
            user.getEmail(),
            user.getPhoneNumber(),
            user.getAddress(),
            user.getIdentityNumber(),
            user.getIdentityIssuedDate(),
            user.getIdentityIssuedPlace(),
            user.getGender(),
            new java.sql.Timestamp(System.currentTimeMillis())
        );

        if (user.getTierId() != null) {
            String insertLoyaltySql = """
                INSERT INTO user_loyalty_profiles (user_id, current_tier_id)
                VALUES (?, ?)
            """;
            jdbcTemplate.update(insertLoyaltySql, userId, user.getTierId());
        }
        return user;
    }
}

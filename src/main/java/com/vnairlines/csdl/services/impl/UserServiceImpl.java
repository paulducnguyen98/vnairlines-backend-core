package com.vnairlines.csdl.services.impl;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;
import java.util.concurrent.ThreadLocalRandom;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.vnairlines.csdl.dtos.MembershipTierDto;
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

        jdbcTemplate.update(insertUserSql, userId, user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getPhoneNumber(), user.getAddress(), user.getIdentityNumber(), user.getIdentityIssuedDate(),
                user.getIdentityIssuedPlace(), user.getGender(), createdAt);

        if (user.getTierName() != null && !user.getTierName().isBlank()) {
            // 🔍 Get tier_id from tier_name
            String findTierSql = """
                        SELECT tier_id FROM membership_tiers WHERE LOWER(tier_name) = LOWER(?)
                    """;

            List<UUID> tierIds = jdbcTemplate.query(findTierSql,
                    (rs, rowNum) -> UUID.fromString(rs.getString("tier_id")), user.getTierName());

            if (!tierIds.isEmpty()) {
                UUID tierId = tierIds.get(0);
                String insertLoyaltySql = """
                            INSERT INTO user_loyalty_profiles (user_id, current_tier_id)
                            VALUES (?, ?)
                        """;
                jdbcTemplate.update(insertLoyaltySql, userId, tierId);
            } else {
                throw new IllegalArgumentException("Tier name not found: " + user.getTierName());
            }
        }

        return user;
    }

    @Override
    public UserDto updateUser(UserDto user) {
        String updateUserSql = """
                    UPDATE users
                    SET first_name = ?, last_name = ?, email = ?, phone_number = ?, address = ?,
                        identity_number = ?, identity_issued_date = ?, identity_issued_place = ?, gender = ?
                    WHERE user_id = ?
                """;

        jdbcTemplate.update(updateUserSql, user.getFirstName(), user.getLastName(), user.getEmail(),
                user.getPhoneNumber(), user.getAddress(), user.getIdentityNumber(), user.getIdentityIssuedDate(),
                user.getIdentityIssuedPlace(), user.getGender(), user.getUserId());

        // Optional: update loyalty tier if tierName is provided
        if (user.getTierName() != null && !user.getTierName().isBlank()) {
            // Lookup tier_id from tier_name
            String findTierSql = """
                        SELECT tier_id FROM membership_tiers WHERE LOWER(tier_name) = LOWER(?)
                    """;

            List<UUID> tierIds = jdbcTemplate.query(findTierSql,
                    (rs, rowNum) -> UUID.fromString(rs.getString("tier_id")), user.getTierName());

            if (!tierIds.isEmpty()) {
                UUID tierId = tierIds.get(0);

                // Try to update existing record first
                int updated = jdbcTemplate.update("""
                            UPDATE user_loyalty_profiles
                            SET current_tier_id = ?
                            WHERE user_id = ?
                        """, tierId, user.getUserId());

                // If not exist, insert new record
                if (updated == 0) {
                    String membershipCode = generateMembershipCode();
                    jdbcTemplate.update("""
                                INSERT INTO user_loyalty_profiles (user_id, current_tier_id, membership_code, miles_expiry_date)
                            VALUES (?, ?, ?, NOW() + INTERVAL '12 months')
                        """, user.getUserId(), tierId, membershipCode);
                }
            } else {
                throw new IllegalArgumentException("Tier name not found: " + user.getTierName());
            }
        }

        return user;
    }

    private String generateMembershipCode() {
        long code = ThreadLocalRandom.current().nextLong(0, 10000000000L);
        return String.format("%010d", code);
    }

    @Override
    public void assignTierToUser(UUID userId, String tierName) {
        // Get tier_id by tier_name
        String getTierIdSql = "SELECT tier_id FROM membership_tiers WHERE LOWER(tier_name) = LOWER(?)";
        UUID tierId = jdbcTemplate.queryForObject(getTierIdSql, UUID.class, tierName);

        if (tierId == null) {
            throw new IllegalArgumentException("Tier not found: " + tierName);
        }

        // Check if user already has a loyalty profile
        String checkSql = "SELECT COUNT(*) FROM user_loyalty_profiles WHERE user_id = ?";
        int count = jdbcTemplate.queryForObject(checkSql, Integer.class, userId);

        if (count > 0) {
            String updateSql = "UPDATE user_loyalty_profiles SET current_tier_id = ? WHERE user_id = ?";
            jdbcTemplate.update(updateSql, tierId, userId);
        } else {
            String insertSql = "INSERT INTO user_loyalty_profiles (user_id, current_tier_id) VALUES (?, ?)";
            jdbcTemplate.update(insertSql, userId, tierId);
        }
    }

    @Override
    public List<MembershipTierDto> getAllMembershipTiers() {
        String sql = """
                    SELECT tier_id, tier_name, tier_rank, required_miles, required_flights, benefits
                    FROM membership_tiers
                    ORDER BY tier_rank
                """;

        return jdbcTemplate.query(sql, (rs, rowNum) -> {
            MembershipTierDto dto = new MembershipTierDto();
            dto.setTierId(UUID.fromString(rs.getString("tier_id")));
            dto.setTierName(rs.getString("tier_name"));
            dto.setTierRank(rs.getInt("tier_rank"));
            dto.setRequiredMiles(rs.getInt("required_miles"));
            dto.setRequiredFlights(rs.getInt("required_flights"));
            dto.setBenefits(rs.getString("benefits"));
            return dto;
        });
    }

    @Override
    public boolean isPhoneNumberTaken(String phoneNumber, UUID excludeUserId) {
        String sql = "SELECT COUNT(*) FROM users WHERE phone_number = ?";
        List<Object> params = new ArrayList<>();
        params.add(phoneNumber);

        if (excludeUserId != null) {
            sql += " AND user_id <> ?";
            params.add(excludeUserId);
        }

        Integer count = jdbcTemplate.queryForObject(sql, Integer.class, params.toArray());
        return count != null && count > 0;
    }


}

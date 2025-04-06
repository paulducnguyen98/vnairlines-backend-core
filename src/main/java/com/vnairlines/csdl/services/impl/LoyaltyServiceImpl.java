package com.vnairlines.csdl.services.impl;

import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.vnairlines.csdl.dtos.MileRedemptionDto;
import com.vnairlines.csdl.dtos.MileTransactionDto;
import com.vnairlines.csdl.dtos.UserLoyaltyProfileDto;
import com.vnairlines.csdl.services.LoyaltyService;

@Service
public class LoyaltyServiceImpl implements LoyaltyService {

    @Autowired
    private JdbcTemplate jdbcTemplate;

    @Override
    public UserLoyaltyProfileDto getLoyaltyProfile(UUID userId) {
        UserLoyaltyProfileDto dto = jdbcTemplate.queryForObject("""
                    SELECT u.user_id, u.first_name, u.last_name, t.tier_name,
                           lp.total_miles, lp.total_flights, lp.miles_expiry_date, lp.tier_achieved_at, lp.membership_code 
                    FROM user_loyalty_profiles lp
                    JOIN users u ON u.user_id = lp.user_id
                    LEFT JOIN membership_tiers t ON lp.current_tier_id = t.tier_id
                    WHERE lp.user_id = ?
                """, (rs, rowNum) -> {
            UserLoyaltyProfileDto profile = new UserLoyaltyProfileDto();
            profile.setUserId(UUID.fromString(rs.getString("user_id")));
            profile.setFullName(rs.getString("first_name") + " " + rs.getString("last_name"));
            profile.setTierName(rs.getString("tier_name"));
            profile.setTotalMiles(rs.getInt("total_miles"));
            profile.setMemberCode(rs.getString("membership_code"));
            profile.setTotalFlights(rs.getInt("total_flights"));
            profile.setMilesExpiryDate(rs.getDate("miles_expiry_date").toLocalDate());
            profile.setTierAchievedAt(rs.getTimestamp("tier_achieved_at").toLocalDateTime());
            return profile;
        }, userId);

        // Load mile transactions
        List<MileTransactionDto> transactions = jdbcTemplate.query("""
                    SELECT transaction_id, flight_id, miles_change, reason, transaction_time
                    FROM miles_transactions
                    WHERE user_id = ?
                """, (ResultSet rs, int rowNum) -> {
            MileTransactionDto tx = new MileTransactionDto();
            tx.setTransactionId(UUID.fromString(rs.getString("transaction_id")));
            tx.setFlightId((UUID) rs.getObject("flight_id"));
            tx.setMilesChange(rs.getInt("miles_change"));
            tx.setReason(rs.getString("reason"));
            tx.setTransactionTime(rs.getTimestamp("transaction_time").toLocalDateTime());
            return tx;
        }, userId);

        // Load redemptions
        List<MileRedemptionDto> redemptions = jdbcTemplate.query("""
                    SELECT redemption_id, miles_used, redeemed_for, related_ticket_id, redeemed_at
                    FROM mile_redemptions
                    WHERE user_id = ?
                """, (ResultSet rs, int rowNum) -> {
            MileRedemptionDto r = new MileRedemptionDto();
            r.setRedemptionId(UUID.fromString(rs.getString("redemption_id")));
            r.setMilesUsed(rs.getInt("miles_used"));
            r.setRedeemedFor(rs.getString("redeemed_for"));
            r.setRelatedTicketId((UUID) rs.getObject("related_ticket_id"));
            r.setRedeemedAt(rs.getTimestamp("redeemed_at").toLocalDateTime());
            return r;
        }, userId);

        dto.setTransactions(transactions);
        dto.setRedemptions(redemptions);

        return dto;
    }
}

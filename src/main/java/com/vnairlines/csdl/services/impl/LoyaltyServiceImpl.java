package com.vnairlines.csdl.services.impl;

import java.sql.ResultSet;
import java.util.List;
import java.util.UUID;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.stereotype.Service;

import com.vnairlines.csdl.dtos.AirportSummary;
import com.vnairlines.csdl.dtos.FlightSummary;
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

//        // Load mile transactions
//        List<MileTransactionDto> transactions = jdbcTemplate.query("""
//                    SELECT transaction_id, flight_id, miles_change, reason, transaction_time
//                    FROM miles_transactions
//                    WHERE user_id = ?
//                """, (ResultSet rs, int rowNum) -> {
//            MileTransactionDto tx = new MileTransactionDto();
//            tx.setTransactionId(UUID.fromString(rs.getString("transaction_id")));
//            tx.setFlightId((UUID) rs.getObject("flight_id"));
//            tx.setMilesChange(rs.getInt("miles_change"));
//            tx.setReason(rs.getString("reason"));
//            tx.setTransactionTime(rs.getTimestamp("transaction_time").toLocalDateTime());
//            return tx;
//        }, userId);
        List<MileTransactionDto> transactions = jdbcTemplate.query("""
                SELECT 
                    mt.transaction_id, mt.flight_id, mt.miles_change, mt.reason, mt.transaction_time,
                    f.flight_id AS f_flight_id, f.flight_number, f.departure_time, f.arrival_time,
                    da.airport_code AS dep_airport_code, da.airport_name AS dep_airport_name, da.city AS dep_city, da.country AS dep_country,
                    aa.airport_code AS arr_airport_code, aa.airport_name AS arr_airport_name, aa.city AS arr_city, aa.country AS arr_country
                FROM miles_transactions mt
                LEFT JOIN flights f ON mt.flight_id = f.flight_id
                LEFT JOIN airports da ON f.departure_airport_id = da.airport_id
                LEFT JOIN airports aa ON f.arrival_airport_id = aa.airport_id
                WHERE mt.user_id = ?
            """, (ResultSet rs, int rowNum) -> {
        MileTransactionDto tx = new MileTransactionDto();
        tx.setTransactionId(UUID.fromString(rs.getString("transaction_id")));
        tx.setFlightId((UUID) rs.getObject("flight_id"));
        tx.setMilesChange(rs.getInt("miles_change"));
        tx.setReason(rs.getString("reason"));
        tx.setTransactionTime(rs.getTimestamp("transaction_time").toLocalDateTime());

        // If flight exists, map the flight details
        if (rs.getObject("f_flight_id") != null) {
            FlightSummary flight = new FlightSummary();
            flight.setFlightId(UUID.fromString(rs.getString("f_flight_id")));
            flight.setFlightNumber(rs.getString("flight_number"));
            flight.setDepartureTime(rs.getTimestamp("departure_time").toLocalDateTime());
            flight.setArrivalTime(rs.getTimestamp("arrival_time").toLocalDateTime());

            // Map departure airport info
            AirportSummary depAirport = new AirportSummary();
            depAirport.setAirportCode(rs.getString("dep_airport_code"));
            depAirport.setAirportName(rs.getString("dep_airport_name"));
            depAirport.setCity(rs.getString("dep_city"));
            depAirport.setCountry(rs.getString("dep_country"));
            flight.setDepartureAirport(depAirport);

            // Map arrival airport info
            AirportSummary arrAirport = new AirportSummary();
            arrAirport.setAirportCode(rs.getString("arr_airport_code"));
            arrAirport.setAirportName(rs.getString("arr_airport_name"));
            arrAirport.setCity(rs.getString("arr_city"));
            arrAirport.setCountry(rs.getString("arr_country"));
            flight.setArrivalAirport(arrAirport);

            tx.setFlight(flight);
        }
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

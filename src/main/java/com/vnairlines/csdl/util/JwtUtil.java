package com.vnairlines.csdl.util;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import java.security.Key;
import java.util.Date;

public class JwtUtil {
    private static final Key key = Keys.secretKeyFor(SignatureAlgorithm.HS256); // You can persist this key securely

    public static String generateToken(String userId, String email) {
        long expirationTime = 1000 * 60 * 60 * 24; // 1 day in milliseconds
        return Jwts.builder().setSubject(userId).claim("email", email).setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expirationTime)).signWith(key).compact();
    }
}

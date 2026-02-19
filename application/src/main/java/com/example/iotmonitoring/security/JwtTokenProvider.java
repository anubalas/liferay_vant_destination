package com.example.iotmonitoring.security;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.stereotype.Component;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;

/**
 * JwtTokenProvider is responsible for creating and validating JWT tokens.
 */
@Component
public class JwtTokenProvider {
    private final String SECRET_KEY = "your-secure-key"; // Use environment variable in production
    private final long EXPIRATION_TIME = 86400000; // 1 day in milliseconds

    /**
     * Creates a JWT token for the given username.
     * @param username the username for which the token is created
     * @return the generated JWT token
     */
    public String createToken(String username) {
        Map<String, Object> claims = new HashMap<>();
        return Jwts.builder()
                .setClaims(claims)
                .setSubject(username)
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(new Date(System.currentTimeMillis() + EXPIRATION_TIME))
                .signWith(SignatureAlgorithm.HS512, SECRET_KEY)
                .compact();
    }

    /**
     * Validates the given JWT token.
     * @param token the JWT token to validate
     * @return true if the token is valid, false otherwise
     */
    public boolean validateToken(String token) {
        try {
            Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token);
            return true;
        } catch (Exception e) {
            return false;
        }
    }

    /**
     * Extracts the username from the given JWT token.
     * @param token the JWT token
     * @return the username extracted from the token
     */
    public String getUsernameFromToken(String token) {
        Claims claims = Jwts.parser().setSigningKey(SECRET_KEY).parseClaimsJws(token).getBody();
        return claims.getSubject();
    }
}
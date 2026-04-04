package com.gyana.Gyana_Social.config;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;

import javax.crypto.SecretKey;

import org.springframework.stereotype.Component;
import org.springframework.security.core.Authentication;

import java.util.Date;

@Component
public class JwtProvider {

    private static final SecretKey key =
            Keys.hmacShaKeyFor(JwtConstant.SECRET_KEY.getBytes());

    // Generate JWT Token
    public static String generateToken(Authentication auth) {

        long now = System.currentTimeMillis();

        String jwt = Jwts.builder()
                .setIssuer("Gyana_social")
                .setIssuedAt(new Date(now))
                .setExpiration(new Date(now + 864000000)) // 10 days
                .claim("email", auth.getName())
                .signWith(key)
                .compact();

        return jwt;
    }

    // Extract Email From Token
    public static String getEmailFromJwtToken(String jwt) {

        if (jwt.startsWith("Bearer ")) {
            jwt = jwt.substring(7);
        }

        Claims claims = Jwts.parserBuilder()
                .setSigningKey(key)
                .build()
                .parseClaimsJws(jwt)
                .getBody();

        return claims.get("email", String.class);
    }
}
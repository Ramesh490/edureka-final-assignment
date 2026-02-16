package com.edureka.auth.service;

import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.security.Keys;
import org.springframework.stereotype.Service;

import java.security.Key;
import java.util.Date;

@Service
public class JwtService {

    private static final String SECRET =
            "X9kP2vLm8QwRtYz12345JwtSecretKeySecure2026HSPXXxxghtf";

    private final Key key = Keys.hmacShaKeyFor(SECRET.getBytes());

    @SuppressWarnings("deprecation")
    public String generateToken(String username, String role) {

        return Jwts.builder()
                .setSubject(username)
                .claim("roles", "ROLE_" + role)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60))
                .signWith(key, SignatureAlgorithm.HS256)
                .compact();
    }
}

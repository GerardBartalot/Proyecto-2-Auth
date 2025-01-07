package com.example.Auth.Pr2.services.impl;

import com.example.Auth.Pr2.commons.dtos.TokenResponse;
import com.example.Auth.Pr2.services.JwtService;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import java.util.Date;

import io.jsonwebtoken.security.Keys;

@Service
public class JwtServiceImpl implements JwtService {
    private final byte[] secretKey;

    public JwtServiceImpl(@Value("${jwt.secret}") String secretToken) {
        this.secretKey = secretToken.getBytes();
    }

    @Override
    public TokenResponse generateToken(Long userId) {
        Date expirationDate = new Date(Long.MAX_VALUE);

        String token = Jwts.builder()
                .setSubject(String.valueOf(userId))
                .setIssuedAt(new Date(System.currentTimeMillis()))
                .setExpiration(expirationDate)
                .signWith(Keys.hmacShaKeyFor(secretKey), SignatureAlgorithm.HS512) // Usamos Keys.hmacShaKeyFor
                .compact();

        return TokenResponse.builder()
                .accessToken(token)
                .build();
    }

    @Override
    public Claims getClaims(String token) {
        return Jwts.parserBuilder()
                .setSigningKey(Keys.hmacShaKeyFor(secretKey)) // Usamos Keys.hmacShaKeyFor
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    @Override
    public boolean isExpired(String token) {
        try {
            return getClaims(token).getExpiration().before(new Date());
        } catch (Exception e) {
            return false;
        }
    }

    @Override
    public Integer extractUserId(String token) {
        try {
            return Integer.parseInt(getClaims(token).getSubject());
        } catch (Exception e) {
            return null;
        }
    }
}


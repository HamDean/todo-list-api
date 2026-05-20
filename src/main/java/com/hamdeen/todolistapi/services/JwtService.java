package com.hamdeen.todolistapi.services;

import com.hamdeen.todolistapi.configs.JwtConfig;
import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.security.Keys;
import lombok.AllArgsConstructor;
import org.springframework.stereotype.Service;

import javax.crypto.SecretKey;
import java.security.Key;
import java.util.Date;

@Service
@AllArgsConstructor
public class JwtService {
   private final JwtConfig jwtConfig;

    public String generateAccessToken (String username) {
        return generateToken(username, jwtConfig.getAccessTokenExpiration());
    }

    public String generateRefreshToken (String username) {
        return generateToken(username, jwtConfig.getRefreshTokenExpiration());
    }

    public String generateToken(String username, long tokenExpiration) {
        return Jwts.builder()
                .subject(username)
                .issuedAt(new Date())
                .expiration(new Date(System.currentTimeMillis() + 1000 * tokenExpiration))
                .signWith(getSecretKey())
                .compact();
    }

    public boolean validateToken (String token) {
        var claims = getClaims(token);

        return claims.getExpiration().after(new Date());
    }

    private Claims getClaims(String token) {
        return Jwts.parser()
            .verifyWith((SecretKey) getSecretKey())
            .build()
            .parseSignedClaims(token)
            .getPayload();
    }

    public String getPrincipalFromToken (String token) {
        return getClaims(token).getSubject();
    }

    public Key getSecretKey() {
        return Keys.hmacShaKeyFor(jwtConfig.getSecret().getBytes());
    }
}

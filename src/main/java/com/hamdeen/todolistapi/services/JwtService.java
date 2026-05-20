package com.hamdeen.todolistapi.services;

import com.hamdeen.todolistapi.configs.JwtConfig;
import com.hamdeen.todolistapi.entities.User;
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

    public String generateAccessToken (User user) {
        return generateToken(user, jwtConfig.getAccessTokenExpiration());
    }

    public String generateRefreshToken (User user) {
        return generateToken(user, jwtConfig.getRefreshTokenExpiration());
    }

    public String generateToken(User user, long tokenExpiration) {
        return Jwts.builder()
                .subject(user.getId().toString())
                .claim("email", user.getEmail())
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

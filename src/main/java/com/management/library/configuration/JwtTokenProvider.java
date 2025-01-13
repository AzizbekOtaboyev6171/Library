package com.management.library.configuration;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.SignatureAlgorithm;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import lombok.Getter;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;

import java.security.Key;
import java.util.Date;
import java.util.function.Function;

@Slf4j
@Getter
@Component
public class JwtTokenProvider {
    @Value("${jwt.access-secret}")
    private String accessSecret;

    @Value("${jwt.refresh-secret}")
    private String refreshSecret;

    @Value("${jwt.access-expiration}")
    private long accessExpiration;

    @Value("${jwt.refresh-expiration}")
    private long refreshExpiration;

    private static final SignatureAlgorithm SIGNATURE_ALGORITHM = SignatureAlgorithm.HS256;

    private String generateToken(String subject, long expiration, String secret, Claims claims) {
        return Jwts.builder()
                .setSubject(subject)
                .addClaims(claims)
                .setIssuedAt(new Date())
                .setExpiration(new Date(System.currentTimeMillis() + expiration))
                .signWith(getSignInKey(secret), SIGNATURE_ALGORITHM)
                .compact();
    }

    public String generateAccessToken(PrincipalUser principalUser) {
        Claims claims = Jwts.claims().setSubject(principalUser.getUsername());
//        claims.put("roles", principalUser.getRoles().stream()
//                .map(Role::getName)
//                .collect(Collectors.toList()));
        log.info("Generating access token for user: {}", principalUser.getUsername());
        return generateToken(principalUser.getUsername(), accessExpiration, accessSecret, claims);
    }

    public String generateRefreshToken(PrincipalUser principalUser) {
        log.info("Generating refresh token for user: {}", principalUser.getUsername());
        return generateToken(String.valueOf(principalUser.getId()), refreshExpiration, refreshSecret, Jwts.claims());
    }

    public boolean isAccessTokenValid(String token) {
        return isTokenValid(token, extractSubject(token, accessSecret), accessSecret);
    }

    public boolean isRefreshTokenValid(String token) {
        return isTokenValid(token, extractSubject(token, refreshSecret), refreshSecret);
    }

    private boolean isTokenValid(String token, String identifier, String secret) {
        final String extractedIdentifier = extractSubject(token, secret);
        return (extractedIdentifier.equals(identifier)) && !isTokenExpired(token, secret);
    }

    private boolean isTokenExpired(String token, String secret) {
        return extractExpiration(token, secret).before(new Date());
    }

    public String extractSubject(String token, String secret) {
        return extractClaim(token, Claims::getSubject, secret);
    }

    public String getUsernameFromAccessToken(String token) {
        return extractSubject(token, accessSecret);
    }

    public String getUsernameFromRefreshToken(String token) {
        return extractSubject(token, refreshSecret);
    }

    private Date extractExpiration(String token, String secret) {
        return extractClaim(token, Claims::getExpiration, secret);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver, String secret) {
        final Claims claims = extractAllClaims(token, secret);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token, String secret) {
        return Jwts.parserBuilder()
                .setSigningKey(getSignInKey(secret))
                .build()
                .parseClaimsJws(token)
                .getBody();
    }

    private Key getSignInKey(String secret) {
        byte[] keyBytes = Decoders.BASE64.decode(secret);
        return Keys.hmacShaKeyFor(keyBytes);
    }
}
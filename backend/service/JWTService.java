package com.relink.backend.service;

import io.jsonwebtoken.Claims;
import io.jsonwebtoken.Jwts;
import io.jsonwebtoken.io.Decoders;
import io.jsonwebtoken.security.Keys;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.stereotype.Service;

import javax.crypto.KeyGenerator;
import javax.crypto.SecretKey;
import java.security.NoSuchAlgorithmException;
import java.util.Base64;
import java.util.Date;
import java.util.HashMap;
import java.util.Map;
import java.util.function.Function;

@Service
public class JWTService {

    private String secretKey = "";
    private final TokenBlacklistService blacklistService;

    public JWTService(TokenBlacklistService blacklistService, TokenBlacklistService blacklistService1){
        this.blacklistService = blacklistService1;
        try{
            KeyGenerator keyGen = KeyGenerator.getInstance("HmacSHA256");
            SecretKey sk = keyGen.generateKey();
            secretKey = Base64.getEncoder().encodeToString(sk.getEncoded());
            //System.out.println("This is the newly generated token " + secretKey);
        } catch (NoSuchAlgorithmException e) {
            throw new RuntimeException(e);
        }
    }

    public String generateToken(String email) {
        Map<String, Object> claims = new HashMap<>();

        return Jwts.builder()
                .setClaims(claims)                    // FIXED: Use setClaims() instead of claims()
                .setSubject(email)                    // FIXED: Use setSubject() instead of subject()
                .setIssuedAt(new Date(System.currentTimeMillis()))  // FIXED: Use setIssuedAt()
                .setExpiration(new Date(System.currentTimeMillis() + 1000 * 60 * 60 * 24))  // FIXED: Use setExpiration()
                .signWith(getKey())
                .compact();
    }

    private SecretKey getKey() {
        byte[] keyBytes = Decoders.BASE64.decode(secretKey);
        return Keys.hmacShaKeyFor(keyBytes);
    }

    public String extractEmail(String token) {
        return extractClaim(token, Claims::getSubject);
    }

    private <T> T extractClaim(String token, Function<Claims, T> claimsResolver){
        final Claims claims = extractAllClaims(token);
        return claimsResolver.apply(claims);
    }

    private Claims extractAllClaims(String token) {
        return Jwts.parserBuilder()              // FIXED: Use parserBuilder() instead of parser()
                .setSigningKey(getKey())         // FIXED: Use setSigningKey() instead of verifyWith()
                .build()
                .parseClaimsJws(token)           // FIXED: Use parseClaimsJws() instead of parseSignedClaims()
                .getBody();                      // FIXED: Use getBody() instead of getPayload()
    }

    public boolean validateToken(String token, UserDetails userDetails) {

        if(blacklistService.isBlacklisted(token)){
            return false;
        }
        final String email = extractEmail(token);
        return (email.equals(userDetails.getUsername()) && !isTokenExpired(token));
    }

    private boolean isTokenExpired(String token) {
        return extractExpiration(token).before(new Date());
    }

    private Date extractExpiration(String token) {
        return extractClaim(token, Claims::getExpiration);
    }
}
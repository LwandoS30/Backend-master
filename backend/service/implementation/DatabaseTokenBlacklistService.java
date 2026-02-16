package com.relink.backend.service.implementation;

import com.relink.backend.model.BlacklistedToken;
import lombok.AllArgsConstructor;
import lombok.RequiredArgsConstructor;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.time.LocalTime;

@Service
@RequiredArgsConstructor
public class DatabaseTokenBlacklistService {

    private BlacklistedToken blacklistedTokenRepository;

    public  void blacklistToken(String token, LocalDateTime expiresAt){
        BlacklistedToken blacklisted = new BlacklistedToken();

        blacklisted.setToken(token);
        blacklisted.setBlacklistedAt(LocalDateTime.now());
        blacklisted.setExpiresAt(expiresAt);

        blacklistedTokenRepository.save(blacklisted);
    }

    public boolean isBlacklisted(String token){
        return blacklistedTokenRepository.existByToken(token);
    }
}

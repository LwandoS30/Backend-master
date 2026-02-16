package com.relink.backend.service;

import org.springframework.stereotype.Service;

import java.util.Set;
import java.util.concurrent.ConcurrentHashMap;

@Service
public class TokenBlacklistService {

    private  final Set<String> blacklistTokens = ConcurrentHashMap.newKeySet();

    public void blacklistToken(String token){
        blacklistTokens.add(token);
    }

    public boolean isBlacklisted(String token){
        return blacklistTokens.contains(token);
    }

    public void removeToken(String token){
        blacklistTokens.remove(token);
    }
}

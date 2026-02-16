package com.relink.backend.repository;

import com.relink.backend.model.BlacklistedToken;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.time.LocalDateTime;

@Repository

public interface BlacklistedTokenRepository extends JpaRepository<BlacklistedTokenRepository, Long> {
    boolean existsByToken(String token);
    void deleteByExpiresAtBefore(LocalDateTime dateTime);
}

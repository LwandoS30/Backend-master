package com.relink.backend.service.implementation;

import com.relink.backend.model.BlacklistedToken;
import org.springframework.data.jpa.repository.JpaRepository;

interface BlacklistedTokenRepository extends JpaRepository<BlacklistedToken, Long> {
}

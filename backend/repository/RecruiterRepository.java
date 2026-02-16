package com.relink.backend.repository;

import com.relink.backend.model.Recruiter;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.UUID;

public interface RecruiterRepository extends JpaRepository<Recruiter, UUID> {

    boolean existsByEmail(String email);
    boolean existsByUsername(String username);
}

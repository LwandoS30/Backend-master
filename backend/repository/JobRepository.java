package com.relink.backend.repository;

import com.relink.backend.model.Job;
import org.springframework.data.jpa.repository.JpaRepository;

import java.util.List;
import java.util.UUID;

public interface JobRepository extends JpaRepository<Job,UUID> {

    List<Job> findByRecruiterId(UUID recruiterId);

}

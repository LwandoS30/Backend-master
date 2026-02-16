package com.relink.backend.service;

import com.relink.backend.model.Recruiter;
import com.relink.backend.record.CreateRecruiterRequest;

import java.util.UUID;

public interface RecruiterService {

    Recruiter createRecruiter(CreateRecruiterRequest request);

    void deleteRecruiter(UUID id);

    public Recruiter getRecruiterById(UUID id);
}

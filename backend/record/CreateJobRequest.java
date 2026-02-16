package com.relink.backend.record;

import com.relink.backend.enums.JobStatus;

import java.time.LocalDateTime;

public record CreateJobRequest(

            String title,
            String description,
            String location,
            LocalDateTime closingDate,
            String salary
) {}


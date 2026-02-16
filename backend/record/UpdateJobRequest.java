package com.relink.backend.record;

import com.relink.backend.enums.JobStatus;

import java.time.LocalDateTime;

public record UpdateJobRequest(
        String title,
        String description,
        String location,
        LocalDateTime closingDAte,
        String salary,
        JobStatus status

) {
}

package com.relink.backend.dto;

import com.relink.backend.enums.JobStatus;
import java.time.LocalDateTime;
import java.util.UUID;

public record JobDto(
        UUID id,
        String title,
        String location,
        String description,
        String Salary,
        JobStatus status,
        LocalDateTime closingDate
) {
}

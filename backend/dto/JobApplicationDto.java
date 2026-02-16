package com.relink.backend.dto;

import java.time.Instant;
import java.time.LocalDateTime;

public record JobApplicationDto(
    String applicantName,
    String applicantID,
    Instant timeApplied
) {
}

package com.relink.backend.record;

import com.relink.backend.enums.JobApplicationStatus;

import java.util.UUID;

public record CreateJobApplicationRequest(
        UUID jobId,
        JobApplicationStatus status,
        String applicantName,
        String applicantEmail
) {

}

package com.relink.backend.dto;

import com.relink.backend.enums.VerificationStatus;
import jakarta.validation.constraints.NotNull;

public class DocumentStatusUpdateRequest {

    @NotNull(message = "Status is required")
    private VerificationStatus status;

    private String reviewNotes;

    public DocumentStatusUpdateRequest() {
    }

    public DocumentStatusUpdateRequest(VerificationStatus status, String reviewNotes) {
        this.status = status;
        this.reviewNotes = reviewNotes;
    }

    public VerificationStatus getStatus() {
        return status;
    }

    public void setStatus(VerificationStatus status) {
        this.status = status;
    }

    public String getReviewNotes() {
        return reviewNotes;
    }

    public void setReviewNotes(String reviewNotes) {
        this.reviewNotes = reviewNotes;
    }
}
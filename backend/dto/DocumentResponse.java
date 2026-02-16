package com.relink.backend.dto;

import com.relink.backend.enums.VerificationStatus;
import com.relink.backend.model.VerificationDocument;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class DocumentResponse {

    private Long docId;
    private String docName;
    private String file;
    private LocalDate expiryDate;
    private String docType;
    private Long userId;
    private Long contactPersonId;
    private String documentReferenceNumber;
    private VerificationStatus status;
    private LocalDateTime uploadedAt;
    private LocalDateTime reviewedAt;
    private String reviewNotes;
    private Boolean isProtected;
    private String documentId;

    public DocumentResponse() {
    }

    public DocumentResponse(VerificationDocument document) {
        this.docId = document.getDocId();
        this.docName = document.getDocName();
        this.file = document.getFile();
        this.expiryDate = document.getExpiryDate();
        this.docType = document.getDocType();
        this.userId = document.getUserId();
        this.contactPersonId = document.getContactPersonId();
        this.documentReferenceNumber = document.getDocumentReferenceNumber();
        this.status = document.getStatus();
        this.uploadedAt = document.getUploadedAt();
        this.reviewedAt = document.getReviewedAt();
        this.reviewNotes = document.getReviewNotes();
        this.isProtected = document.getIsProtected();
        this.documentId = document.getDocumentId();
    }

    // Getters and Setters
    public Long getDocId() {
        return docId;
    }

    public void setDocId(Long docId) {
        this.docId = docId;
    }

    public String getDocName() {
        return docName;
    }

    public void setDocName(String docName) {
        this.docName = docName;
    }

    public String getFile() {
        return file;
    }

    public void setFile(String file) {
        this.file = file;
    }

    public LocalDate getExpiryDate() {
        return expiryDate;
    }

    public void setExpiryDate(LocalDate expiryDate) {
        this.expiryDate = expiryDate;
    }

    public String getDocType() {
        return docType;
    }

    public void setDocType(String docType) {
        this.docType = docType;
    }

    public Long getUserId() {
        return userId;
    }

    public void setUserId(Long userId) {
        this.userId = userId;
    }

    public Long getContactPersonId() {
        return contactPersonId;
    }

    public void setContactPersonId(Long contactPersonId) {
        this.contactPersonId = contactPersonId;
    }

    public String getDocumentReferenceNumber() {
        return documentReferenceNumber;
    }

    public void setDocumentReferenceNumber(String documentReferenceNumber) {
        this.documentReferenceNumber = documentReferenceNumber;
    }

    public VerificationStatus getStatus() {
        return status;
    }

    public void setStatus(VerificationStatus status) {
        this.status = status;
    }

    public LocalDateTime getUploadedAt() {
        return uploadedAt;
    }

    public void setUploadedAt(LocalDateTime uploadedAt) {
        this.uploadedAt = uploadedAt;
    }

    public LocalDateTime getReviewedAt() {
        return reviewedAt;
    }

    public void setReviewedAt(LocalDateTime reviewedAt) {
        this.reviewedAt = reviewedAt;
    }

    public String getReviewNotes() {
        return reviewNotes;
    }

    public void setReviewNotes(String reviewNotes) {
        this.reviewNotes = reviewNotes;
    }

    public Boolean getIsProtected() {
        return isProtected;
    }

    public void setIsProtected(Boolean isProtected) {
        this.isProtected = isProtected;
    }

    public String getDocumentId() {
        return documentId;
    }

    public void setDocumentId(String documentId) {
        this.documentId = documentId;
    }
}
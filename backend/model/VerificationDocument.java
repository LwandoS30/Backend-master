package com.relink.backend.model;

import com.relink.backend.enums.VerificationStatus;
import jakarta.persistence.*;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.util.UUID;

@Entity
@Table(name = "documents")
public class VerificationDocument {

    // Primary Key - matches ERD
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column(name = "doc_id")
    private Long docId;

    // ERD Fields
    @Column(name = "doc_name", nullable = false)
    private String docName;

    @Column(name = "file", nullable = false)
    private String file; // URL to the file (can be cloud storage URL)

    @Column(name = "expiry_date")
    private LocalDate expiryDate;

    @Column(name = "doc_type", nullable = false)
    private String docType;

    // Foreign Keys from ERD
    @Column(name = "user_id", nullable = false)
    private Long userId;

    @Column(name = "contact_person_id")
    private Long contactPersonId;

    // Additional fields for verification workflow (not in ERD but needed)
    @Column(name = "document_reference_number", unique = true, nullable = false, updatable = false)
    private String documentReferenceNumber;

    @Enumerated(EnumType.STRING)
    @Column(name = "status", nullable = false)
    private VerificationStatus status;

    @Column(name = "uploaded_at", nullable = false)
    private LocalDateTime uploadedAt;

    @Column(name = "reviewed_at")
    private LocalDateTime reviewedAt;

    @Column(name = "review_notes")
    private String reviewNotes;

    @Column(name = "is_protected")
    private Boolean isProtected;

    @Column(name = "document_id")
    private String documentId; // External cloud storage ID

    @PrePersist
    public void generateReferenceNumber() {
        if (this.documentReferenceNumber == null) {
            this.documentReferenceNumber = String.format("DOC-%d-%d-%s",
                    this.userId,
                    System.currentTimeMillis(),
                    UUID.randomUUID().toString().substring(0, 8).toUpperCase()
            );
        }
        if (this.uploadedAt == null) {
            this.uploadedAt = LocalDateTime.now();
        }
        if (this.status == null) {
            this.status = VerificationStatus.PENDING;
        }
    }

    // Constructors
    public VerificationDocument() {
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
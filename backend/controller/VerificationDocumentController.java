package com.relink.backend.controller;

import com.relink.backend.dto.*;
import com.relink.backend.model.VerificationDocument;
import com.relink.backend.service.VerificationDocumentService;
import com.relink.backend.enums.VerificationStatus;
import jakarta.validation.Valid;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/api/verification")
@CrossOrigin(origins = "*")
public class VerificationDocumentController {

    private final VerificationDocumentService verificationDocumentService;

    public VerificationDocumentController(VerificationDocumentService verificationDocumentService) {
        this.verificationDocumentService = verificationDocumentService;
    }

    @PostMapping("/documents/submit")
    public ResponseEntity<DocumentResponse> submitDocument(@Valid @RequestBody DocumentUploadRequest request) {
        VerificationDocument document = verificationDocumentService.createDocument(request);
        DocumentResponse response = new DocumentResponse(document);
        return ResponseEntity.status(HttpStatus.CREATED).body(response);
    }

    @GetMapping("/users/{userId}/documents")
    public ResponseEntity<List<DocumentResponse>> getUserDocuments(@PathVariable Long userId) {
        List<VerificationDocument> documents = verificationDocumentService.getDocumentsByUser(userId);
        List<DocumentResponse> responses = documents.stream()
                .map(DocumentResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/documents/{documentId}/details")
    public ResponseEntity<DocumentResponse> getDocumentDetails(@PathVariable Long documentId) {
        try {
            VerificationDocument document = verificationDocumentService.getDocumentById(documentId);
            DocumentResponse response = new DocumentResponse(document);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @GetMapping("/documents/{documentId}/check-status")
    public ResponseEntity<String> checkDocumentStatus(@PathVariable Long documentId) {
        try {
            VerificationDocument document = verificationDocumentService.getDocumentById(documentId);
            return ResponseEntity.ok("Document status: " + document.getStatus());
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document not found");
        }
    }

    @GetMapping("/admin/documents/all")
    public ResponseEntity<List<DocumentResponse>> getAllDocuments() {
        List<VerificationDocument> documents = verificationDocumentService.getAllDocuments();
        List<DocumentResponse> responses = documents.stream()
                .map(DocumentResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/admin/documents/pending")
    public ResponseEntity<List<DocumentResponse>> getPendingDocuments() {
        List<VerificationDocument> documents = verificationDocumentService.getDocumentsByStatus(VerificationStatus.PENDING);
        List<DocumentResponse> responses = documents.stream()
                .map(DocumentResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/admin/documents/approved")
    public ResponseEntity<List<DocumentResponse>> getApprovedDocuments() {
        List<VerificationDocument> documents = verificationDocumentService.getDocumentsByStatus(VerificationStatus.APPROVED);
        List<DocumentResponse> responses = documents.stream()
                .map(DocumentResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @GetMapping("/admin/documents/rejected")
    public ResponseEntity<List<DocumentResponse>> getRejectedDocuments() {
        List<VerificationDocument> documents = verificationDocumentService.getDocumentsByStatus(VerificationStatus.REJECTED);
        List<DocumentResponse> responses = documents.stream()
                .map(DocumentResponse::new)
                .collect(Collectors.toList());
        return ResponseEntity.ok(responses);
    }

    @PutMapping("/documents/{documentId}/update")
    public ResponseEntity<DocumentResponse> updateDocumentInfo(
            @PathVariable Long documentId,
            @Valid @RequestBody DocumentUpdateRequest request) {
        try {
            VerificationDocument updatedDocument = verificationDocumentService.updateDocument(documentId, request);
            DocumentResponse response = new DocumentResponse(updatedDocument);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/admin/documents/{documentId}/approve")
    public ResponseEntity<DocumentResponse> approveDocument(
            @PathVariable Long documentId,
            @RequestParam(required = false) String reviewNotes) {
        try {
            VerificationDocument updatedDocument = verificationDocumentService.updateStatus(
                    documentId,
                    VerificationStatus.APPROVED,
                    reviewNotes
            );
            DocumentResponse response = new DocumentResponse(updatedDocument);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/admin/documents/{documentId}/reject")
    public ResponseEntity<DocumentResponse> rejectDocument(
            @PathVariable Long documentId,
            @RequestParam(required = false) String reviewNotes) {
        try {
            VerificationDocument updatedDocument = verificationDocumentService.updateStatus(
                    documentId,
                    VerificationStatus.REJECTED,
                    reviewNotes
            );
            DocumentResponse response = new DocumentResponse(updatedDocument);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @PatchMapping("/admin/documents/{documentId}/review")
    public ResponseEntity<DocumentResponse> markForReview(
            @PathVariable Long documentId,
            @RequestParam(required = false) String reviewNotes) {
        try {
            VerificationDocument updatedDocument = verificationDocumentService.updateStatus(
                    documentId,
                    VerificationStatus.UNDER_REVIEW,
                    reviewNotes
            );
            DocumentResponse response = new DocumentResponse(updatedDocument);
            return ResponseEntity.ok(response);
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).build();
        }
    }

    @DeleteMapping("/documents/{documentId}/delete")
    public ResponseEntity<String> deleteDocument(@PathVariable Long documentId) {
        try {
            verificationDocumentService.deleteDocument(documentId);
            return ResponseEntity.ok("Document deleted successfully");
        } catch (IllegalArgumentException e) {
            return ResponseEntity.status(HttpStatus.NOT_FOUND).body("Document not found");
        }
    }
}
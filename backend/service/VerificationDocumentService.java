package com.relink.backend.service;

import com.relink.backend.dto.DocumentUploadRequest;
import com.relink.backend.dto.DocumentUpdateRequest;
import com.relink.backend.model.VerificationDocument;
import com.relink.backend.repository.VerificationDocumentRepository;
import com.relink.backend.enums.VerificationStatus;
import org.springframework.stereotype.Service;

import java.time.LocalDateTime;
import java.util.List;

@Service
public class VerificationDocumentService {

    private final VerificationDocumentRepository verificationDocumentRepository;

    public VerificationDocumentService(VerificationDocumentRepository verificationDocumentRepository) {
        this.verificationDocumentRepository = verificationDocumentRepository;
    }

    public VerificationDocument createDocument(DocumentUploadRequest request) {
        VerificationDocument document = new VerificationDocument();
        document.setUserId(request.getUserId());
        document.setDocName(request.getDocName());
        document.setDocType(request.getDocType());
        document.setFile(request.getFile());
        document.setExpiryDate(request.getExpiryDate());
        document.setContactPersonId(request.getContactPersonId());
        document.setDocumentId(request.getDocumentId());
        document.setStatus(VerificationStatus.PENDING);
        document.setUploadedAt(LocalDateTime.now());
        document.setIsProtected(request.getIsProtected() != null ? request.getIsProtected() : false);

        return verificationDocumentRepository.save(document);
    }

    public List<VerificationDocument> getDocumentsByUser(Long userId) {
        return verificationDocumentRepository.findByUserId(userId);
    }

    public VerificationDocument getDocumentById(Long docId) {
        return verificationDocumentRepository.findById(docId)
                .orElseThrow(() -> new IllegalArgumentException("Document not found with ID: " + docId));
    }

    public VerificationDocument updateDocument(Long docId, DocumentUpdateRequest request) {
        VerificationDocument document = getDocumentById(docId);

        if (request.getDocName() != null) {
            document.setDocName(request.getDocName());
        }
        if (request.getFile() != null) {
            document.setFile(request.getFile());
        }
        if (request.getExpiryDate() != null) {
            document.setExpiryDate(request.getExpiryDate());
        }
        if (request.getDocType() != null) {
            document.setDocType(request.getDocType());
        }
        if (request.getContactPersonId() != null) {
            document.setContactPersonId(request.getContactPersonId());
        }
        if (request.getIsProtected() != null) {
            document.setIsProtected(request.getIsProtected());
        }
        if (request.getDocumentId() != null) {
            document.setDocumentId(request.getDocumentId());
        }

        return verificationDocumentRepository.save(document);
    }

    public VerificationDocument updateStatus(Long docId, VerificationStatus status, String reviewNotes) {
        VerificationDocument document = getDocumentById(docId);
        document.setStatus(status);
        document.setReviewedAt(LocalDateTime.now());
        if (reviewNotes != null && !reviewNotes.isEmpty()) {
            document.setReviewNotes(reviewNotes);
        }
        return verificationDocumentRepository.save(document);
    }

    public void deleteDocument(Long docId) {
        VerificationDocument document = getDocumentById(docId);
        verificationDocumentRepository.delete(document);
    }

    public List<VerificationDocument> getAllDocuments() {
        return verificationDocumentRepository.findAll();
    }

    public List<VerificationDocument> getDocumentsByStatus(VerificationStatus status) {
        return verificationDocumentRepository.findByStatus(status);
    }
}
//```
//
//        ---
//
//        ## 📬 **Updated Postman Test**
//        ```
//POST http://localhost:8080/api/verification/documents/submit
//
//Body:
//        {
//        "userId": 1,
//        "docName": "National ID Card",
//        "docType": "ID_CARD",
//        "file": "https://cloudinary.com/uploads/user1-id.jpg",
//        "expiryDate": "2030-12-31",
//        "contactPersonId": 1,
//        "isProtected": false
//        }
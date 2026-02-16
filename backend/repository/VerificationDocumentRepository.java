package com.relink.backend.repository;

import com.relink.backend.model.VerificationDocument;
import com.relink.backend.enums.VerificationStatus;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface VerificationDocumentRepository extends JpaRepository<VerificationDocument, Long> {

    List<VerificationDocument> findByUserId(Long userId);

    List<VerificationDocument> findByStatus(VerificationStatus status);

    List<VerificationDocument> findByUserIdAndStatus(Long userId, VerificationStatus status);

    // Changed from documentType to docType to match the model
    List<VerificationDocument> findByDocType(String docType);
}


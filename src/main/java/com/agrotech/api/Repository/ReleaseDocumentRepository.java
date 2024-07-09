package com.agrotech.api.Repository;

import com.agrotech.api.model.ReleaseDocument;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReleaseDocumentRepository extends MongoRepository<ReleaseDocument, String> {

    Optional<ReleaseDocument> findByCode(String code);
    Page<ReleaseDocument> findByCodeContainingIgnoreCase(String code, Pageable pageable);
    Page<ReleaseDocument> findByIsDeletedAndCodeContainingIgnoreCase(Boolean isDeleted, String code, Pageable pageable);
    Page<ReleaseDocument> findByIsDeletedAndCodeContainingIgnoreCaseAndFarmer(Boolean isDeleted, String code,String farmer, Pageable pageable);
}


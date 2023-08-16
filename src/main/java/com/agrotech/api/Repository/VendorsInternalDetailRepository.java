package com.agrotech.api.Repository;

import com.agrotech.api.model.VendorsInternalDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorsInternalDetailRepository extends MongoRepository<VendorsInternalDetails, String> {


    Optional<VendorsInternalDetails> findById(String id);
    Page<VendorsInternalDetails> findByIdContainingIgnoreCase(String id, Pageable pageable);
    Page<VendorsInternalDetails> findByIsDeletedAndIdContainingIgnoreCase(Boolean isDeleted, String id, Pageable pageable);
    Page<VendorsInternalDetails> findByIsDeleted(Boolean isDeleted, Pageable pageable);
}

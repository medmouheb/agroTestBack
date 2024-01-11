package com.agrotech.api.Repository;

import com.agrotech.api.model.VendorsPayments;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorsPaymentRepository extends MongoRepository<VendorsPayments, String> {

    Optional<VendorsPayments> findById(String Id);
    Page<VendorsPayments> findByIdContainingIgnoreCase(String id, Pageable pageable);
    Page<VendorsPayments> findByIsDeletedAndIdContainingIgnoreCase(Boolean isDeleted, String id, Pageable pageable);
    Page<VendorsPayments> findByIsDeleted(Boolean isDeleted, Pageable pageable);
}

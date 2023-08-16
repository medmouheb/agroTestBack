package com.agrotech.api.Repository;

import com.agrotech.api.model.VendorTypeDetails;
import com.agrotech.api.model.VendorsShipping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorTypeDetailsRepository extends MongoRepository<VendorTypeDetails,String> {


    Optional<VendorTypeDetails> findById(String id);

    Page<VendorTypeDetails> findByOrderLineContainingIgnoreCase(String OrderLine, Pageable pageable);

    Page<VendorTypeDetails> findByIsDeletedAndOrderLineContainingIgnoreCase(Boolean isDeleted, String OrderLine, Pageable pageable);

    Page<VendorTypeDetails> findByIsDeleted(Boolean isDeleted, Pageable pageable);

    VendorTypeDetails findByOrderLine(String OrderLine);
}
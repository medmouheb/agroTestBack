package com.agrotech.api.Repository;

import com.agrotech.api.model.Vendors;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorsRepository extends MongoRepository<Vendors, String> {

    Optional<Vendors> findByVendorCode(String VendorCode);
    Page<Vendors> findByVendorName01ContainingIgnoreCase(String VendorName01, Pageable pageable);
    Page<Vendors> findByIsDeletedAndVendorName01ContainingIgnoreCase(Boolean isDeleted, String VendorName01, Pageable pageable);
    Page<Vendors> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    Vendors findByVendorName01(String VendorName01 );
}

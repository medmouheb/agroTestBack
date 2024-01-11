package com.agrotech.api.Repository;

import com.agrotech.api.model.VendorSKU;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface VendorSKURepository extends MongoRepository<VendorSKU, String> {

    Page<VendorSKU> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<VendorSKU> findByIsDeletedAndVendorSKUNameContainingIgnoreCase(Boolean isDeleted, String vendorSKUName, Pageable pageable);

    Optional<VendorSKU> findByCode(String code);
    VendorSKU findByName(String name);
    VendorSKU findByVendorSKUName(String name);

}

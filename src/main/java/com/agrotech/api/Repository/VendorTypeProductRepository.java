package com.agrotech.api.Repository;


import com.agrotech.api.model.VendorTypeProduct;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorTypeProductRepository extends MongoRepository<VendorTypeProduct, String> {


    Optional<VendorTypeProduct> findByProductCode(String productCode);

    Page<VendorTypeProduct> findByProductNameContainingIgnoreCase(String ProductName, Pageable pageable);

    Page<VendorTypeProduct> findByIsDeletedAndProductNameContainingIgnoreCase(Boolean isDeleted, String ProductName, Pageable pageable);


    Page<VendorTypeProduct> findByIsDeleted(Boolean isDeleted, Pageable pageable);

    VendorTypeProduct findByProductName(String ProductName);
}

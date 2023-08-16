package com.agrotech.api.Repository;

import com.agrotech.api.model.Vendors;
import com.agrotech.api.model.VendorsShipping;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorsShippingRepository extends MongoRepository<VendorsShipping,String> {

    Optional<VendorsShipping> findByShippingLocationCode(String ShippingLocationCode);
    Page<VendorsShipping> findByShippingLocationNameContainingIgnoreCase(String ShippingLocationName, Pageable pageable);
    Page<VendorsShipping> findByIsDeletedAndShippingLocationNameContainingIgnoreCase(Boolean isDeleted, String ShippingLocationName, Pageable pageable);
    Page<VendorsShipping> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    VendorsShipping findByShippingLocationName(String ShippingLocationName );
}

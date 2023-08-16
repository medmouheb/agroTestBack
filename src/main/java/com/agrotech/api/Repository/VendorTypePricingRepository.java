package com.agrotech.api.Repository;

import com.agrotech.api.model.VendorTypePODetails;
import com.agrotech.api.model.VendorTypePricing;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;


@Repository
public interface VendorTypePricingRepository extends MongoRepository<VendorTypePricing, String> {


    Optional<VendorTypePricing> findByCurrencyCode(String CurrencyCode);
    Page<VendorTypePricing> findByCurrencyNameContainingIgnoreCase(String CurrencyName, Pageable pageable);
    Page<VendorTypePricing> findByIsDeletedAndCurrencyNameContainingIgnoreCase(Boolean isDeleted, String CurrencyName, Pageable pageable);
    Page<VendorTypePricing> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    VendorTypePricing findByCurrencyName(String CurrencyName );
}

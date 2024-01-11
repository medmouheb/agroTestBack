package com.agrotech.api.Repository;

import com.agrotech.api.model.VendorTypePODetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorTypePODetailsRepository extends MongoRepository<VendorTypePODetails, String> {



    Optional<VendorTypePODetails> findById(String id);
    Page<VendorTypePODetails> findByBuyerNameContainingIgnoreCase(String BuyerName, Pageable pageable);
    Page<VendorTypePODetails> findByIsDeletedAndBuyerNameContainingIgnoreCase(Boolean isDeleted, String BuyerName, Pageable pageable);
    Page<VendorTypePODetails> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    VendorTypePODetails findByBuyerName(String BuyerName );
}

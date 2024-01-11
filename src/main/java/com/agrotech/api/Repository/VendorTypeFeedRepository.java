package com.agrotech.api.Repository;

import com.agrotech.api.model.VendorTypeFeed;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorTypeFeedRepository extends MongoRepository<VendorTypeFeed,String> {


    Optional<VendorTypeFeed> findById(String code);
    Page<VendorTypeFeed> findByPOStateContainingIgnoreCase(String POState, Pageable pageable);
    Page<VendorTypeFeed> findByIsDeletedAndPOStateContainingIgnoreCase(Boolean isDeleted, String name, Pageable pageable);
    Page<VendorTypeFeed> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    VendorTypeFeed findByPOState(String POState );
}

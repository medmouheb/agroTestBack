package com.agrotech.api.Repository;

import com.agrotech.api.model.VendorTypePurchaser;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorTypePurchaserRepository extends MongoRepository<VendorTypePurchaser,String> {


    Optional<VendorTypePurchaser> findById(String id);
    Page<VendorTypePurchaser> findByIdContainingIgnoreCase(String id, Pageable pageable);
    Page<VendorTypePurchaser> findByIsDeletedAndIdContainingIgnoreCase(Boolean isDeleted, String id, Pageable pageable);
    Page<VendorTypePurchaser> findByIsDeleted(Boolean isDeleted, Pageable pageable);
}

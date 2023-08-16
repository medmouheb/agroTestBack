package com.agrotech.api.Repository;

import com.agrotech.api.model.Campany;
import com.agrotech.api.model.VendorTypeReceiving;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorTypeReceivingRepository extends MongoRepository<VendorTypeReceiving, String> {

    Optional<VendorTypeReceiving> findById(String id);
    Page<VendorTypeReceiving> findByIdContainingIgnoreCase(String id, Pageable pageable);
    Page<VendorTypeReceiving> findByIsDeletedAndIdContainingIgnoreCase(Boolean isDeleted, String id, Pageable pageable);
    Page<VendorTypeReceiving> findByIsDeleted(Boolean isDeleted, Pageable pageable);

}

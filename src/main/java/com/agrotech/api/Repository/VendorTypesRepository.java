package com.agrotech.api.Repository;

import com.agrotech.api.model.VendorTypes;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorTypesRepository extends MongoRepository<VendorTypes,String> {


    Optional<VendorTypes> findById(String id);
    Page<VendorTypes> findByIdContainingIgnoreCase(String id, Pageable pageable);
    Page<VendorTypes> findByIsDeletedAndIdContainingIgnoreCase(Boolean isDeleted, String id, Pageable pageable);
    Page<VendorTypes> findByIsDeleted(Boolean isDeleted, Pageable pageable);


}

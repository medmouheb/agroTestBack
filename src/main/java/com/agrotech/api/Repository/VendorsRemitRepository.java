package com.agrotech.api.Repository;

import com.agrotech.api.model.Campany;
import com.agrotech.api.model.VendorsRemit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorsRemitRepository extends MongoRepository<VendorsRemit,String> {


    Optional<VendorsRemit> findById(String id);
    Page<VendorsRemit> findByRemitToNameContainingIgnoreCase(String RemitToName, Pageable pageable);
    Page<VendorsRemit> findByIsDeletedAndRemitToNameContainingIgnoreCase(Boolean isDeleted, String RemitToName, Pageable pageable);
    Page<VendorsRemit> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    VendorsRemit findByRemitToName(String RemitToName );
}

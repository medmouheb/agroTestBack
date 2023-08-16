package com.agrotech.api.Repository;

import com.agrotech.api.model.Campany;
import com.agrotech.api.model.VendorTypeFreight;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorTypeFreightRepository extends MongoRepository<VendorTypeFreight, String> {


    Optional<VendorTypeFreight> findByFreightTermCode(String FreightTermCode);
    Page<VendorTypeFreight> findByFreightTermNameContainingIgnoreCase(String FreightTermName, Pageable pageable);
    Page<VendorTypeFreight> findByIsDeletedAndFreightTermNameContainingIgnoreCase(Boolean isDeleted, String FreightTermName, Pageable pageable);
    Page<VendorTypeFreight> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    VendorTypeFreight findByFreightTermName(String FreightTermName );
}

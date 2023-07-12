package com.agrotech.api.Repository;

import com.agrotech.api.model.VendorSKU;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.agrotech.api.model.Fournisseur;
import com.agrotech.api.model.Warehouse;

import java.util.Optional;

@Repository
public interface WarehouseRepository extends MongoRepository<Warehouse, String> {
    Page<Warehouse> findByNameContainingIgnoreCase(String name, Pageable pageable);

    Optional<Warehouse> findByCode(String code);
    Page<Warehouse> findByIsDeletedAndNameContainingIgnoreCase(Boolean isDeleted, String vendorSKUName, Pageable pageable);


}

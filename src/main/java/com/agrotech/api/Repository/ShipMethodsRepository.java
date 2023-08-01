package com.agrotech.api.Repository;

import com.agrotech.api.model.Campany;
import com.agrotech.api.model.ShipMethods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface ShipMethodsRepository extends MongoRepository<ShipMethods, String > {

    Optional<ShipMethods> findByCode(String code);
    Page<ShipMethods> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<ShipMethods> findByIsDeletedAndNameContainingIgnoreCase(Boolean isDeleted, String name, Pageable pageable);
    Page<ShipMethods> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    ShipMethods findByName(String name );
}

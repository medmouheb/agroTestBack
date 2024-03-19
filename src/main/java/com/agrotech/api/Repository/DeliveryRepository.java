package com.agrotech.api.Repository;


import com.agrotech.api.model.Crop;
import com.agrotech.api.model.Delivery;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryRepository  extends MongoRepository<Delivery, String> {
    Optional<Delivery> findByCode(String code);
    Page<Delivery> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Delivery> findByIsDeletedAndNameContainingIgnoreCase(Boolean isDeleted, String name, Pageable pageable);
    Page<Delivery> findByIsDeletedAndNameContainingIgnoreCaseAndFarmer(Boolean isDeleted, String name,String farmer, Pageable pageable);
    Page<Delivery> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    Delivery findByName(String name );
}

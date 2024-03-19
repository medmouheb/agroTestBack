package com.agrotech.api.Repository;

import com.agrotech.api.model.Delivery;
import com.agrotech.api.model.Devis;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DevisRepository extends MongoRepository<Devis, String> {
    Optional<Devis> findByCode(String code);
    Page<Devis> findByCodeContainingIgnoreCase(String code, Pageable pageable);
    Page<Devis> findByIsDeletedAndCodeContainingIgnoreCase(Boolean isDeleted, String code, Pageable pageable);
    Page<Devis> findByIsDeletedAndCodeContainingIgnoreCaseAndFarmer(Boolean isDeleted, String code,String farmer, Pageable pageable);
}

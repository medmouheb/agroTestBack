package com.agrotech.api.Repository;

import com.agrotech.api.model.Facture;
import com.agrotech.api.model.PotentialClient;
import com.agrotech.api.model.Production;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ProductionRepository extends MongoRepository<Production, String> {
    Optional<Facture> findByCode(String code);
    Page<Facture> findByCodeContainingIgnoreCase(String code, Pageable pageable);
    Page<Facture> findByIsDeletedAndCodeContainingIgnoreCase(Boolean isDeleted, String code, Pageable pageable);
    Page<Facture> findByIsDeletedAndCodeContainingIgnoreCaseAndFarmer(Boolean isDeleted, String code,String farmer, Pageable pageable);
}

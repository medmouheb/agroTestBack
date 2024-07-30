package com.agrotech.api.Repository;

import com.agrotech.api.model.Willaya;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface WillayaRepository extends MongoRepository<Willaya, String> {

    Optional<Willaya> findByCode(String code);
    Page<Willaya> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Willaya> findByIsDeletedAndNameContainingIgnoreCase(Boolean isDeleted, String vendorSKUName, Pageable pageable);
    Page<Willaya> findByFarmerAndIsDeletedAndNameContainingIgnoreCase(String farmer,Boolean isDeleted, String vendorSKUName, Pageable pageable);
    Willaya findByName(String name);

    Optional<Willaya> findByCodeAndFarmer(String code, String farmer);

    List<Willaya> findByFarmer(String farmer);
}

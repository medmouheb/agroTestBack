package com.agrotech.api.Repository;

import java.util.List;
import java.util.Optional;

import com.agrotech.api.model.Crop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.agrotech.api.model.Division;

@Repository
public interface DivisionRepository extends MongoRepository<Division, String> {
    Optional<Division> findByCodeAndFarmer(String code, String farmer);

    Optional<Division> findByCode(String code);
    Page<Division> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Division> findByIsDeletedAndNameContainingIgnoreCase(Boolean isDeleted, String name, Pageable pageable);
    Page<Division> findByFarmerAndIsDeletedAndNameContainingIgnoreCase(String farmer,Boolean isDeleted, String name, Pageable pageable);
    Division findByName(String name);

    List<Division> findByFarmer(String farmer);
}

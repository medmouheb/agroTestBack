package com.agrotech.api.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.agrotech.api.model.Campany;

@Repository
public interface CampanyRepository extends MongoRepository<Campany, String> {

    Optional<Campany> findByCode(String code);
    Page<Campany> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Campany> findByIsDeletedAndNameContainingIgnoreCase(Boolean isDeleted, String name, Pageable pageable);
    Page<Campany> findByIsDeletedAndNameContainingIgnoreCaseAndFarmer(Boolean isDeleted, String name,String farmer, Pageable pageable);
    Page<Campany> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    Campany findByName(String name );
}

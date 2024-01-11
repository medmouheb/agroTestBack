package com.agrotech.api.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.agrotech.api.model.Ferme;

@Repository
public interface FermeRepository extends MongoRepository<Ferme, String> {

    Optional<Ferme> findByCode(String code);
    Page<Ferme> findByNomContainingIgnoreCase(String name, Pageable pageable);
    Page<Ferme> findByIsDeletedAndNomContainingIgnoreCase(Boolean isDeleted, String name, Pageable pageable);
    Ferme findByNom(String nom);
}

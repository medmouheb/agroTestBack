package com.agrotech.api.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.agrotech.api.model.Fournisseur;

import java.util.List;
import java.util.Optional;

@Repository
public interface FournisseurRepository extends MongoRepository<Fournisseur, String> {
    Page<Fournisseur> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Fournisseur> findByNameContainingIgnoreCaseAndIsDeleted(String name,Boolean isDeleted, Pageable pageable);
    Page<Fournisseur> findByFarmerAndNameContainingIgnoreCaseAndIsDeleted(String farmer,String name,Boolean isDeleted, Pageable pageable);

    Optional<Fournisseur> findByCode(String code);
    Optional<Fournisseur> findByCodeAndFarmer(String code, String farmer);
    Page<Fournisseur> findByIsDeleted(Boolean isDeleted,  Pageable pageable);
    Fournisseur findByName(String name);
    List<Fournisseur> findByFarmer(String farmer);
    Page<Fournisseur> findByIsDeletedAndNameContainingIgnoreCase(Boolean isDeleted, String name, Pageable pageable);

}

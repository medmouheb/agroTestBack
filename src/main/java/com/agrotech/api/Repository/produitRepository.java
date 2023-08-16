package com.agrotech.api.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.agrotech.api.model.Produit;

import java.util.List;
import java.util.Optional;


@Repository
public interface produitRepository extends MongoRepository<Produit, String>{
	Optional<Produit> findByCode(String code);

    Page<Produit> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Produit findByName(String name);
    Page<Produit> findByIsDeletedAndNameContainingIgnoreCase(Boolean isDeleted, String name, Pageable pageable);


}

package com.agrotech.api.Repository;

import com.agrotech.api.model.Tax;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface TaxRepository extends MongoRepository<Tax, String> {
    Optional<Tax> findByCode(String code);
    Page<Tax> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Tax> findByIsDeletedAndNameContainingIgnoreCase(Boolean isDeleted, String name, Pageable pageable);
    Page<Tax> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    Tax findByName(String name );
}

package com.agrotech.api.Repository;

import com.agrotech.api.model.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface StockRepository extends MongoRepository<Stock, String> {
    Optional<Stock> findByCode(String code);
    Page<Stock> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Stock> findByIsDeletedAndNameContainingIgnoreCase(Boolean isDeleted, String name, Pageable pageable);
    Page<Stock> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    Stock findByName(String name );
}

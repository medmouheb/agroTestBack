package com.agrotech.api.Repository;

import com.agrotech.api.model.Stock;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;
import java.util.Optional;

public interface StockRepository extends MongoRepository<Stock, String> , StockRepositoryCustom {
    Optional<Stock> findByCodeAndFarmer(String code , String farmer);
    List<Stock> findByFarmer(String farmer);
    Page<Stock> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Stock> findByIsDeletedAndNameContainingIgnoreCase(Boolean isDeleted, String name, Pageable pageable);
    Page<Stock> findByFarmerAndIsDeletedAndNameContainingIgnoreCase(String farmer,Boolean isDeleted, String name, Pageable pageable);
    Page<Stock> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    Stock findByName(String name );
}

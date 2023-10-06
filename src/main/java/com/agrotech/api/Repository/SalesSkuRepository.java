package com.agrotech.api.Repository;


import com.agrotech.api.model.Campany;
import com.agrotech.api.model.SalesSKU;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface SalesSkuRepository extends MongoRepository<SalesSKU, String> {

    Optional<SalesSKU> findByCode(String code);
    Page<SalesSKU> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<SalesSKU> findByIsDeletedAndSailorNameSkuContainingIgnoreCase(Boolean isDeleted, String sailorNameSku, Pageable pageable);
    SalesSKU findByName(String name);
    SalesSKU findBySailorNameSku(String name);

}

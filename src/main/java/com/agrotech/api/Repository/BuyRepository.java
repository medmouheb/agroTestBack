package com.agrotech.api.Repository;

import com.agrotech.api.model.Buy;
import com.agrotech.api.model.Campany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface BuyRepository  extends MongoRepository<Buy, String> {
    Optional<Buy> findByCode(String code);
    Page<Buy> findByCodeContainingIgnoreCase(String code, Pageable pageable);
    Page<Buy> findByIsDeletedAndCodeContainingIgnoreCase(Boolean isDeleted, String code, Pageable pageable);
    Page<Buy> findByIsDeleted(Boolean isDeleted, Pageable pageable);

}

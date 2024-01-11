package com.agrotech.api.Repository;

import com.agrotech.api.model.Charge;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;
public interface ChargeRepository extends MongoRepository<Charge, String>{

    Page<Charge> findBySuppNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Charge> findBySuppNameContainingIgnoreCaseAndIsDeleted(String name,Boolean isDeleted, Pageable pageable);

    Optional<Charge> findBySuppNo(String suppNo);
    Page<Charge> findByIsDeleted(Boolean isDeleted,  Pageable pageable);
    Charge findBySuppName(String paymentTermName);
    Page<Charge> findByIsDeletedAndSuppNameContainingIgnoreCase(Boolean isDeleted, String name, Pageable pageable);

}

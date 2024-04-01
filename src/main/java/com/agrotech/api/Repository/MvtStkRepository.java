package com.agrotech.api.Repository;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.agrotech.api.model.MvtStk;

import java.util.Optional;


@Repository
public interface MvtStkRepository extends MongoRepository<MvtStk, String>{
    Optional<MvtStk> findByCode(String code);
    Page<MvtStk> findByCodeContainingIgnoreCase(String code, Pageable pageable);
    Page<MvtStk> findByIsDeletedAndCodeContainingIgnoreCase(Boolean isDeleted, String code, Pageable pageable);
    Page<MvtStk> findByIsDeletedAndCodeContainingIgnoreCaseAndFarmer(Boolean isDeleted, String code,String farmer, Pageable pageable);

}

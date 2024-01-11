package com.agrotech.api.Repository;

import com.agrotech.api.model.LogisticUnit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface LogisticUnitRepository extends MongoRepository<LogisticUnit, String>{

    Optional<LogisticUnit> findByLogisticCode(String LogisticCode);
    Page<LogisticUnit> findByLogisticNameContainingIgnoreCase(String LogisticName, Pageable pageable);
    Page<LogisticUnit> findByIsDeletedAndLogisticNameContainingIgnoreCase(Boolean isDeleted, String LogisticName, Pageable pageable);
    Page<LogisticUnit> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    LogisticUnit findByLogisticName(String LogisticName);



}

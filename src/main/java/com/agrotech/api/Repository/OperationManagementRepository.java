package com.agrotech.api.Repository;


import com.agrotech.api.model.OperationManagement;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface OperationManagementRepository extends MongoRepository<OperationManagement, String> {
    Optional<OperationManagement> findByOperationId(String operationId);
    Page<OperationManagement> findByNomOperationContainingIgnoreCase(String nomOperation, Pageable pageable);
    Page<OperationManagement> findByIsDeletedAndNomOperationContainingIgnoreCase(Boolean isDeleted, String nomOperation, Pageable pageable);
    Page<OperationManagement> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    OperationManagement findByNomOperation(String nomOperation);

}

package com.agrotech.api.Repository;

import com.agrotech.api.model.DeliveryInstruction;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface DeliveryInstructionRepository  extends MongoRepository <DeliveryInstruction , String> {

    Optional<DeliveryInstruction> findByProductType(String productType);
    Page<DeliveryInstruction> findByProductTypeContainingIgnoreCase(String productType, Pageable pageable);
    Page<DeliveryInstruction> findByIsDeletedAndInstructiuonNameContainingIgnoreCase(Boolean isDeleted, String productType , Pageable pageable);
    Page<DeliveryInstruction> findByIsDeleted(Boolean isDeleted, Pageable pageable);

}

package com.agrotech.api.Repository;

import com.agrotech.api.model.Campany;
import com.agrotech.api.model.VendorTypeHistoryIReceiving;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorTypeHistoryIReceivingRepository extends MongoRepository<VendorTypeHistoryIReceiving, String> {
    Optional<VendorTypeHistoryIReceiving> findById(String id);
    Page<VendorTypeHistoryIReceiving> findByApprovalUserIDContainingIgnoreCase(String ApprovalUserID, Pageable pageable);
    Page<VendorTypeHistoryIReceiving> findByIsDeletedAndApprovalUserIDContainingIgnoreCase(Boolean isDeleted, String ApprovalUserID, Pageable pageable);
    Page<VendorTypeHistoryIReceiving> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    VendorTypeHistoryIReceiving findByApprovalUserID(String ApprovalUserID );
}

package com.agrotech.api.Repository;


import com.agrotech.api.model.DeliveryNote;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface DeliveryNoteRepository   extends MongoRepository<DeliveryNote, String> {
    List<DeliveryNote> findByFarmer(String farmer);
    Optional<DeliveryNote> findByCodeAndFarmer(String code, String farmer);

    Optional<DeliveryNote> findByCode(String code);
    Page<DeliveryNote> findByCodeContainingIgnoreCase(String code, Pageable pageable);
    Page<DeliveryNote> findByIsDeletedAndCodeContainingIgnoreCase(Boolean isDeleted, String code, Pageable pageable);
    Page<DeliveryNote> findByIsDeletedAndCodeContainingIgnoreCaseAndFarmer(Boolean isDeleted, String code,String farmer, Pageable pageable);
}

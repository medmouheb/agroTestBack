package com.agrotech.api.Repository;

import com.agrotech.api.model.BinDetails;
import com.agrotech.api.model.Campany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BinDetailsRepository extends MongoRepository<BinDetails,String> {

    Optional<BinDetails> findByBin(Number bin);
    Page<BinDetails> findByCapacityContainingIgnoreCase(Double Capacity, Pageable pageable);
    Page<BinDetails> findByIsDeletedAndCapacityContainingIgnoreCase(Boolean isDeleted, Double Capacity, Pageable pageable);
    Page<BinDetails> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    BinDetails findByCapacity(Number capacity);
}

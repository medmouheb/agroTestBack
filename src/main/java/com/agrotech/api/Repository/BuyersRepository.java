package com.agrotech.api.Repository;

import com.agrotech.api.model.Buyers;
import com.agrotech.api.model.Campany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BuyersRepository extends MongoRepository<Buyers, String> {

    Optional<Buyers> findByBuyerCode(String BuyerCode);
    Page<Buyers> findByBuyerNameContainingIgnoreCase(String BuyerName, Pageable pageable);
    Page<Buyers> findByIsDeletedAndBuyerNameContainingIgnoreCase(Boolean isDeleted, String BuyerName, Pageable pageable);
    Page<Buyers> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    Buyers findByBuyerName(String BuyerName );
}

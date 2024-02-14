package com.agrotech.api.Repository;

import com.agrotech.api.model.Buyers;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Collection;
import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Repository
public interface BuyersRepository extends MongoRepository<Buyers, String> {

    Optional<Buyers> findByCode(String code);
    Page<Buyers> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Buyers> findByIsDeletedAndNameContainingIgnoreCase(Boolean isDeleted, String name, Pageable pageable);
    Page<Buyers> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    Buyers findByName(String name );

    List<Buyers> findByTagsIn(HashSet<String> tags);
}

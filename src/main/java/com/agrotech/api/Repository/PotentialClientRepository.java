package com.agrotech.api.Repository;


import com.agrotech.api.model.Buyers;
import com.agrotech.api.model.PotentialClient;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.HashSet;
import java.util.List;
import java.util.Optional;

@Repository
public interface PotentialClientRepository  extends MongoRepository<PotentialClient, String> {

    Optional<PotentialClient> findByCode(String code);
    Page<PotentialClient> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<PotentialClient> findByIsDeletedAndNameContainingIgnoreCase(Boolean isDeleted, String name, Pageable pageable);
    Page<PotentialClient> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    PotentialClient findByName(String name );

}

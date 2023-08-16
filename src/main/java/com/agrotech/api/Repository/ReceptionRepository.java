package com.agrotech.api.Repository;

import com.agrotech.api.model.Campany;
import com.agrotech.api.model.Reception;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface ReceptionRepository extends MongoRepository<Reception, String> {

    Optional<Reception> findById(String id);

    Page<Reception> findByIdContainingIgnoreCase(String name, Pageable pageable);

    Page<Reception> findByIsDeletedAndIdContainingIgnoreCase(Boolean isDeleted, String Id, Pageable pageable);


    Page<Reception> findByIsDeleted(Boolean isDeleted, Pageable pageable);

}

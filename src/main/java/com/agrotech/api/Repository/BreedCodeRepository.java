package com.agrotech.api.Repository;

import com.agrotech.api.model.BreedCode;
import com.agrotech.api.model.Campany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BreedCodeRepository extends  MongoRepository<BreedCode, String>{

    Optional<BreedCode> findByBreedCode(String BreedCode);
    Page<BreedCode> findByBreedCodeNameContainingIgnoreCase(String ProductType, Pageable pageable);
    Page<BreedCode> findByIsDeletedAndBreedCodeNameContainingIgnoreCase(Boolean isDeleted, String BreedCodeName, Pageable pageable);
    Page<BreedCode> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    BreedCode findByBreedCodeName(String BreedCodeName);
}

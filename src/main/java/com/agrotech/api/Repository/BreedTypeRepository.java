package com.agrotech.api.Repository;

import com.agrotech.api.model.BreedType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface BreedTypeRepository extends MongoRepository<BreedType, String> {

    Optional<BreedType> findByBreedTypeCode(String BreedTypeCode);
    Page<BreedType> findByBreedTypeNameContainingIgnoreCase(String BreedTypeName, Pageable pageable);
    Page<BreedType> findByIsDeletedAndBreedTypeNameContainingIgnoreCase(Boolean isDeleted, String BreedTypeName, Pageable pageable);
    Page<BreedType> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    BreedType findByBreedTypeName(String BreedTypeName );
}

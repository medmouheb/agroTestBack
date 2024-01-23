package com.agrotech.api.Repository;

import com.agrotech.api.model.Crop;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface CropRepo extends MongoRepository<Crop, String> {
    Optional<Crop> findByCode(String code);
    Page<Crop> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Crop> findByIsDeletedAndNameContainingIgnoreCase(Boolean isDeleted, String name, Pageable pageable);
    Page<Crop> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    Crop findByName(String name );
}

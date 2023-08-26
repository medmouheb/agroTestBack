package com.agrotech.api.Repository;

import com.agrotech.api.model.Drivers;
import com.agrotech.api.model.LogisticUnit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface DriversRepository extends MongoRepository<Drivers, String> {

    Optional<Drivers> findByCodeEmploye(String codeEmploye);
    Page<Drivers> findByNomDuChauffeurContainingIgnoreCase(String nomDuChauffeur, Pageable pageable);
    Page<Drivers> findByIsDeletedAndNomDuChauffeurContainingIgnoreCase(Boolean isDeleted, String nomDuChauffeur, Pageable pageable);
    Page<Drivers> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    Drivers findByNomDuChauffeur(String nomDuChauffeur);

}

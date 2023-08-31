package com.agrotech.api.Repository;

import com.agrotech.api.model.UtilisationDuProduit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface UtilisationDuProduitRepository extends MongoRepository<UtilisationDuProduit, String>{


    Optional<UtilisationDuProduit> findByNumeroDeLot(String codeProduit);
    Page<UtilisationDuProduit> findByNomDuProduitContainingIgnoreCase(String nomDuProduit, Pageable pageable);
    Page<UtilisationDuProduit> findByIsDeletedAndNomDuProduitContainingIgnoreCase(Boolean isDeleted, String nomDuProduit, Pageable pageable);
    Page<UtilisationDuProduit> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    UtilisationDuProduit findByNomDuProduit(String nomDuProduit);

}

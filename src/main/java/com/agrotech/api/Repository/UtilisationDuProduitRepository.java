package com.agrotech.api.Repository;

import com.agrotech.api.model.UtilisationDuProduit;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface UtilisationDuProduitRepository extends MongoRepository<UtilisationDuProduit, String>{


    Optional<UtilisationDuProduit> findByNumeroDeLot(String codeProduit);
    List<UtilisationDuProduit> findByFarmer(String farmer);
    Page<UtilisationDuProduit> findByNomDuProduitContainingIgnoreCase(String nomDuProduit, Pageable pageable);
    Page<UtilisationDuProduit> findByIsDeletedAndNomDuProduitContainingIgnoreCase(Boolean isDeleted, String nomDuProduit, Pageable pageable);
    Page<UtilisationDuProduit> findByIsDeletedAndNumeroDeLotContainingIgnoreCase(Boolean isDeleted, String numeroDeLot, Pageable pageable);
    Page<UtilisationDuProduit> findByFarmerAndIsDeletedAndNumeroDeLotContainingIgnoreCase(String farmer,Boolean isDeleted, String nomDuProduit, Pageable pageable);
    Page<UtilisationDuProduit> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    UtilisationDuProduit findByNomDuProduit(String nomDuProduit);

}

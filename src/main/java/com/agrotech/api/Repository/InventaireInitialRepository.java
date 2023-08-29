package com.agrotech.api.Repository;

import com.agrotech.api.model.InventaireInitial;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface InventaireInitialRepository extends MongoRepository<InventaireInitial, String> {

    Optional<InventaireInitial> findByCodeProduit(String codeProduit);
    Page<InventaireInitial> findByNomDuProduitContainingIgnoreCase(String NomDuProduit, Pageable pageable);
    Page<InventaireInitial> findByIsDeletedAndNomDuProduitContainingIgnoreCase(Boolean isDeleted, String NomDuProduit, Pageable pageable);
    Page<InventaireInitial> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    InventaireInitial findByNomDuProduit(String NomDuProduit);

}

package com.agrotech.api.Repository;

import com.agrotech.api.model.RapprochementDesStocks;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface RapprochementDesStocksRepository extends MongoRepository<RapprochementDesStocks,String> {

    Optional<RapprochementDesStocks> findBynDeReference(String nDeReference);
    Page<RapprochementDesStocks> findByNomDuProduitContainingIgnoreCase(String nomDuProduit, Pageable pageable);
    Page<RapprochementDesStocks> findByIsDeletedAndNomDuProduitContainingIgnoreCase(Boolean isDeleted, String nomDuProduit, Pageable pageable);
    Page<RapprochementDesStocks> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    RapprochementDesStocks findByNomDuProduit(String nomDuProduit);
    Optional<RapprochementDesStocks> findByNumeroDeLot(String numeroDeLot);

}

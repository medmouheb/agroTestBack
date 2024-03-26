package com.agrotech.api.Repository;


import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Repository;

import com.agrotech.api.model.Commande;

import java.util.Optional;
@Repository
@Component
public interface CommandeRepository extends MongoRepository<Commande, String> {
    Page<Commande> findByPaymentTermNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Commande> findByPaymentTermNameContainingIgnoreCaseAndIsDeleted(String name,Boolean isDeleted, Pageable pageable);

    Optional<Commande> findByPaymentTermCode(String paymentTermCode);
    Page<Commande> findByIsDeleted(Boolean isDeleted,  Pageable pageable);
    Commande findByPaymentTermName(String paymentTermName);
    Page<Commande> findByIsDeletedAndPaymentTermNameContainingIgnoreCase(Boolean isDeleted, String name, Pageable pageable);

}

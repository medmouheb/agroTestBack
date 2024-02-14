package com.agrotech.api.Repository;

import com.agrotech.api.model.PaymentMethod;
import com.agrotech.api.model.ShipMethods;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.Optional;

public interface PaymentMethodRepository extends MongoRepository<PaymentMethod, String > {

    Optional<PaymentMethod> findByCode(String code);
    Page<PaymentMethod> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<PaymentMethod> findByIsDeletedAndNameContainingIgnoreCase(Boolean isDeleted, String name, Pageable pageable);
    Page<PaymentMethod> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    PaymentMethod findByName(String name );
}

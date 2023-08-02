package com.agrotech.api.Repository;

import com.agrotech.api.model.Vehicule;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehiculeRepository extends MongoRepository<Vehicule, String> {

    Optional<Vehicule> findByVehiculeCode(String vehiculeCode);

    Page<Vehicule> findByVehiculeCodeContainingIgnoreCase(String vehiculeCode, Pageable pageable);
    Page<Vehicule> findByIsDeletedAndVehiculeCodeContainingIgnoreCase(Boolean isDeleted, String vehiculeCode, Pageable pageable);

    Page<Vehicule> findByIsDeleted(Boolean isDeleted, Pageable pageable);

    Vehicule findByVehiculeName(String vehiculeName);
}

package com.agrotech.api.Repository;

import com.agrotech.api.model.LogisticUnit;
import com.agrotech.api.model.Vehicles;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VehiclesRepository extends MongoRepository<Vehicles,String> {
    Optional<Vehicles> findByCodeVehicule(String codeVehicule);
    Page<Vehicles> findByNomDuVehiculeContainingIgnoreCase(String nomDuVehicule, Pageable pageable);
    Page<Vehicles> findByIsDeletedAndNomDuVehiculeContainingIgnoreCase(Boolean isDeleted, String LogisticName, Pageable pageable);
    Page<Vehicles> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    Vehicles findByNomDuVehicule(String nomDuVehicule);

}

package com.agrotech.api.Repository;


import com.agrotech.api.model.VehicleType;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface VehicleTypeRepository extends MongoRepository<VehicleType, String> {

    Optional<VehicleType> findByVehicleTypeCode(String vehicleTypeCode, String farmer);

    Page<VehicleType> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    Page<VehicleType> findByVehicleTypeNameContainingIgnoreCaseAndIsDeleted(String vehicleTypeName,Boolean isDeleted, Pageable pageable);
    Page<VehicleType> findByFarmerAndVehicleTypeNameContainingIgnoreCaseAndIsDeleted(String farmer,String vehicleTypeName,Boolean isDeleted, Pageable pageable);

    List<VehicleType> findByFarmer(String farmer);
}

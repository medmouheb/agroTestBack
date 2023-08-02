package com.agrotech.api.Repository;


import com.agrotech.api.model.VehicleType;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface VehicleTypeRepository extends MongoRepository<VehicleType, String> {

}

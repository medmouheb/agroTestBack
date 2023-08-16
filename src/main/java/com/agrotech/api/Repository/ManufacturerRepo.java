package com.agrotech.api.Repository;

import com.agrotech.api.model.Manufacturer;
import com.agrotech.api.model.Reason;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ManufacturerRepo extends MongoRepository<Manufacturer, String> {

    boolean existsByManufacturerCode(String manufacturerCode);
    Manufacturer findByManufacturerCode(String manufacturerCode);
    List<Manufacturer> findManufacturerByManufacturerNameContainingIgnoreCaseAndActiveTrue(String manufacturerName);

    List<Manufacturer> findManufacturerByManufacturerNameContainingIgnoreCaseAndActiveFalse(String manufacturerName);
    List<Manufacturer>findManufacturerByActiveTrue();
    List<Manufacturer>findManufacturerByActiveFalse();
}

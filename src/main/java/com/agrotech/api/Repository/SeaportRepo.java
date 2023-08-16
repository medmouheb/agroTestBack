package com.agrotech.api.Repository;

import com.agrotech.api.model.Seaport;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface SeaportRepo extends MongoRepository<Seaport, String> {
    boolean existsBySeaportCode(String seaportCode);
    List<Seaport>findSeaportBySeaportNameContainingIgnoreCaseAndActiveTrue(String seaportName);
    List<Seaport>findSeaportBySeaportNameContainingIgnoreCaseAndActiveFalse(String seaportName);
    Seaport findBySeaportCode(String seaportCode);
    List<Seaport>findSeaportByActiveTrue();
    List<Seaport>findSeaportByActiveFalse();
}

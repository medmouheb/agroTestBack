package com.agrotech.api.Repository;

import com.agrotech.api.model.FacilityDetails;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface FacilityDetailsRepository extends MongoRepository<FacilityDetails, String> {
    Optional<FacilityDetails> findByFacilityID(String facilityID);
    Page<FacilityDetails> findByFacilityNameContainingIgnoreCase(String facilityName, Pageable pageable);
    Page<FacilityDetails> findByIsDeletedAndFacilityNameContainingIgnoreCase(Boolean isDeleted, String FacilityName, Pageable pageable);
    Page<FacilityDetails> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    FacilityDetails findByFacilityName(String FacilityName );

}

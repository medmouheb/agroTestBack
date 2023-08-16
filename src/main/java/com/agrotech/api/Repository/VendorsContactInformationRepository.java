package com.agrotech.api.Repository;

import com.agrotech.api.model.Vehicule;
import com.agrotech.api.model.VendorsContactInformation;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface VendorsContactInformationRepository extends MongoRepository<VendorsContactInformation, String> {

    Optional<VendorsContactInformation> findByLabelCode(String LabelCode);

    Page<VendorsContactInformation> findByLabelNameContainingIgnoreCase(String LabelName, Pageable pageable);
    Page<VendorsContactInformation> findByIsDeletedAndLabelNameContainingIgnoreCase(Boolean isDeleted, String LabelName, Pageable pageable);

    Page<VendorsContactInformation> findByIsDeleted(Boolean isDeleted, Pageable pageable);

    VendorsContactInformation findByLabelName(String LabelName);
}

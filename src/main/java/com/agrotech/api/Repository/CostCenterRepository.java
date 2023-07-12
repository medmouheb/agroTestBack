package com.agrotech.api.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.agrotech.api.model.CostCenter;
import com.agrotech.api.model.Growout;

@Repository
public interface CostCenterRepository extends MongoRepository<CostCenter, String>{
	
	Optional<CostCenter> findByCode(String code);
	Page<CostCenter> findByNameContainingIgnoreCase(String name, Pageable pageable);
	Page<CostCenter> findByIsDeletedAndNameContainingIgnoreCase(Boolean isDeleted, String name, Pageable pageable);

}

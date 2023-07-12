package com.agrotech.api.Repository;

import java.util.Optional;

import com.agrotech.api.model.Campany;
import com.agrotech.api.model.Willaya;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.agrotech.api.model.Sales;

@Repository
public interface SalesRepository extends MongoRepository<Sales, String>{
	
	  Optional<Sales> findByCode(String code);
	Page<Sales> findByNameContainingIgnoreCase(String name, Pageable pageable);
	Page<Sales> findByIsDeletedAndNameContainingIgnoreCase(Boolean isDeleted, String name, Pageable pageable);


}

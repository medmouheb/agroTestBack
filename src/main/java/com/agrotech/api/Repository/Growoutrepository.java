package com.agrotech.api.Repository;

import java.util.Optional;

import com.agrotech.api.model.Campany;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.agrotech.api.model.Growout;



@Repository
public interface Growoutrepository extends MongoRepository<Growout, String> {
	
	Optional<Growout> findByCode(String code );
    Page<Growout> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Growout> findByIsDeletedAndNameContainingIgnoreCase(Boolean isDeleted, String name, Pageable pageable);


}

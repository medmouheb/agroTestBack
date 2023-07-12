package com.agrotech.api.Repository;

import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.agrotech.api.model.Housse;

@Repository
public interface HousseRepository extends MongoRepository<Housse, String> {

	    Optional<Housse> findByCode(String code);
	    Page<Housse> findByCodeContainingIgnoreCase(String code, Pageable pageable);
}

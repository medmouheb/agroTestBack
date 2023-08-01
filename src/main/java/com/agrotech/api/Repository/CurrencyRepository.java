
package com.agrotech.api.Repository;
import java.util.Optional;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;

import com.agrotech.api.model.Currency;

public interface CurrencyRepository extends MongoRepository<Currency, String>{
	
    Optional<Currency> findByCode(String code);
    Page<Currency> findByNameContainingIgnoreCase(String name, Pageable pageable);
    Page<Currency> findByIsDeletedAndNameContainingIgnoreCase(Boolean isDeleted, String name, Pageable pageable);
    Currency findByName(String name);


}

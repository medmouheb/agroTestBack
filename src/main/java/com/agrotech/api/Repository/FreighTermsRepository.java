package com.agrotech.api.Repository;

import com.agrotech.api.model.FreightTerms;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface FreighTermsRepository extends MongoRepository<FreightTerms,String> {
}

package com.agrotech.api.Repository;

import com.agrotech.api.model.Campany;
import com.agrotech.api.model.INCOTerms;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

@Repository
public interface INCOTermsRepository extends MongoRepository<INCOTerms,String> {
Optional<INCOTerms> findByINCOTermCode(String INCOTermCode);
    Page<INCOTerms> findByINCOTermNameContainingIgnoreCase(String INCOTermName, Pageable pageable);
    Page<INCOTerms> findByIsDeletedAndINCOTermNameContainingIgnoreCase(Boolean isDeleted, String INCOTermName, Pageable pageable);
    Page<INCOTerms> findByIsDeleted(Boolean isDeleted, Pageable pageable);
    INCOTerms findByINCOTermName(String INCOTermName );
}

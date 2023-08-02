package com.agrotech.api.Repository;


import com.agrotech.api.model.FreightTerms;
import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;
@Repository
public interface FreighTermsRepository extends MongoRepository<FreightTerms, String> {

    FreightTerms findByFreighttermname(String freighttermname  );
    Optional<FreightTerms> findByFreighttermcode(String freighttermcode );
    Page<FreightTerms> findByFreighttermnameContainingIgnoreCase(String freighttermname, Pageable pageable);
    Page<FreightTerms> findByIsDeletedAndFreighttermnameContainingIgnoreCase(Boolean isDeleted, String freighttermname, Pageable pageable);
    Page<FreightTerms> findByIsDeleted(Boolean isDeleted, Pageable pageable);

}

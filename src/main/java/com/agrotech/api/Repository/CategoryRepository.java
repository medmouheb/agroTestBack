package com.agrotech.api.Repository;

import java.util.Optional;

import org.springframework.data.domain.Page;
import org.springframework.data.domain.Pageable;
import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.agrotech.api.model.Campany;
import com.agrotech.api.model.Category;


@Repository
public interface CategoryRepository extends MongoRepository<Category, String>{
    Optional<Category> findByCode(String code);
    Page<Category> findByNameContainingIgnoreCase(String name, Pageable pageable);


}

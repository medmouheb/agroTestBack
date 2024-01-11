package com.agrotech.api.Repository;

import com.agrotech.api.model.ProductCategory;
import org.springframework.data.mongodb.repository.MongoRepository;

import java.util.List;

public interface ProductCategoryRepo extends MongoRepository<ProductCategory, String> {

    boolean existsByProductCategoryCode(String productCategoryCode);
    ProductCategory findByProductCategoryCode(String productCategoryCode);
    List<ProductCategory>findProductCategoryByProductCategoryNameContainingIgnoreCaseAndActiveTrue(String reasonName);
    List<ProductCategory>findProductCategoryByProductCategoryNameContainingIgnoreCaseAndActiveFalse(String reasonName);

    List<ProductCategory>findProductCategoryByActiveTrue();
    List<ProductCategory>findProductCategoryByActiveFalse();
}

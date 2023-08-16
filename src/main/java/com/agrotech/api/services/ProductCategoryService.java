package com.agrotech.api.services;

import com.agrotech.api.model.ProductCategory;

import java.util.List;

public interface ProductCategoryService {


    ProductCategory ajouterProductCategory(ProductCategory productCategory);
    ProductCategory modifierProductCategory(ProductCategory productCategory);
    List<ProductCategory> getActiveTrueProductCategory();

    void supprimerById(String id);
    boolean productCategoryExists(String id);
    boolean productCategoryCodeExists(String productCategoryCode);
    List<ProductCategory>SearchProductCategoryByNameAndActive(String productCategoryName);
    List<ProductCategory>SearchProductCategoryByNameAndArchived(String productCategoryName);

    ProductCategory getProductCategoryById(String id);
    ProductCategory getProductCategoryByProductCategoryCode(String productCategoryCode);
    List<ProductCategory> getArchivedProductCategory();
}

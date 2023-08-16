package com.agrotech.api.controller;


import com.agrotech.api.dto.AirportDTO;
import com.agrotech.api.dto.ProductCategoryDTO;
import com.agrotech.api.model.Airport;
import com.agrotech.api.model.ProductCategory;
import com.agrotech.api.services.ProductCategoryService;
import org.modelmapper.ModelMapper;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.*;

import java.util.List;
import java.util.stream.Collectors;

@RestController
@RequestMapping("/productCategory")
public class PorductCategoryController {

    @Autowired
    ProductCategoryService productCategoryService;

    @Autowired
    private ModelMapper modelMapper ;

    @PostMapping(value = "")
    public ResponseEntity<?> addProductCategory(@RequestBody @Validated ProductCategory productCategory) {
        if (productCategoryService.productCategoryExists(productCategory.getProductCategoryCode())) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductCategory code is already exist.");
        }
        ProductCategoryDTO newProductCategory = modelMapper.map(productCategoryService.ajouterProductCategory(productCategory), ProductCategoryDTO.class);
        return new ResponseEntity<>(newProductCategory, HttpStatus.CREATED);
    }

    @DeleteMapping(value = "{id}")
    public ResponseEntity<Void> deleteProductCategory(@PathVariable String id) {
        if (productCategoryService.productCategoryExists(id)) {
            productCategoryService.supprimerById(id);
            return new ResponseEntity<>(HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }

    @PutMapping(value = "{id}")
    public ResponseEntity<?> updateProductCategory(@PathVariable String id, @RequestBody @Validated ProductCategory productCategory) {
        if (!productCategoryService.productCategoryExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        // Check if the productcategory code already exists for another airport (excluding the one being updated)
        String updatedAirportCode = productCategory.getProductCategoryCode();
        ProductCategory existingProductCategoryWithCode = productCategoryService.getProductCategoryByProductCategoryCode(updatedAirportCode);
        if (existingProductCategoryWithCode != null && !existingProductCategoryWithCode.getId().equals(id)) {
            return ResponseEntity.status(HttpStatus.CONFLICT).body("ProductCategory code is already in use by another airport.");
        }

        productCategory.setId(id);
        ProductCategoryDTO updatedProductCategory = modelMapper.map(productCategoryService.modifierProductCategory(productCategory), ProductCategoryDTO.class);
        return new ResponseEntity<>(updatedProductCategory, HttpStatus.OK);
    }
    @PatchMapping("deactivate/{id}")
    public ResponseEntity<?> deactivateProductCategory(@PathVariable String id) {
        if (!productCategoryService.productCategoryExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
        productCategory.setActive(false);
        productCategoryService.ajouterProductCategory(productCategory);
        return new ResponseEntity<>(HttpStatus.OK);
    }
    @PatchMapping("activate/{id}")
    public ResponseEntity<?> activateProductCategory(@PathVariable String id) {
        if (!productCategoryService.productCategoryExists(id)) {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }

        ProductCategory productCategory = productCategoryService.getProductCategoryById(id);
        productCategory.setActive(true);
        productCategoryService.ajouterProductCategory(productCategory);
        return new ResponseEntity<>(HttpStatus.OK);
    }


    @GetMapping(value = "active")
    public ResponseEntity<List<ProductCategoryDTO>> getActiveTrueProductCategories() {
        List<ProductCategoryDTO> productCategories = productCategoryService.getActiveTrueProductCategory().stream().map(productCategory -> modelMapper.map(productCategory, ProductCategoryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(productCategories, HttpStatus.OK);
    }
    @GetMapping(value = "archived")
    public ResponseEntity<List<ProductCategoryDTO>> getArchivedProductCategories() {
        List<ProductCategoryDTO> productCategories = productCategoryService.getArchivedProductCategory().stream().map(productCategory -> modelMapper.map(productCategory, ProductCategoryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(productCategories, HttpStatus.OK);
    }

    @GetMapping(value = "{id}")
    public ResponseEntity<ProductCategoryDTO> getProductCategoryById(@PathVariable String id) {
        if (productCategoryService.productCategoryExists(id)) {
            ProductCategoryDTO productCategory = modelMapper.map(productCategoryService.productCategoryExists(id), ProductCategoryDTO.class);
            return new ResponseEntity<>(productCategory, HttpStatus.OK);
        } else {
            return new ResponseEntity<>(HttpStatus.NOT_FOUND);
        }
    }
    @GetMapping(value = "/searchactive")
    public ResponseEntity<List<ProductCategoryDTO>> searchProductCategoryByNameActive(@RequestParam String productCategoryName) {
        List<ProductCategoryDTO> productCategories = productCategoryService.SearchProductCategoryByNameAndActive(productCategoryName).stream().map(productCategory -> modelMapper.map(productCategory, ProductCategoryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(productCategories, HttpStatus.OK);
    }

    @GetMapping(value = "/searcharchived")
    public ResponseEntity<List<ProductCategoryDTO>> searchProductCategoryByNameArchived(@RequestParam String productCategoryName) {
        List<ProductCategoryDTO> productCategories = productCategoryService.SearchProductCategoryByNameAndArchived(productCategoryName).stream().map(productCategory -> modelMapper.map(productCategory, ProductCategoryDTO.class)).collect(Collectors.toList());
        return new ResponseEntity<>(productCategories, HttpStatus.OK);
    }

}

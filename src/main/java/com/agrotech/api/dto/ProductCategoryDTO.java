package com.agrotech.api.dto;


import lombok.Data;

@Data
public class ProductCategoryDTO {
    private String id;
    private String productCategoryType;
    private String productCategoryName;
    private String productCategoryCode;
    private boolean active=true;
    private String notes;

    private String farmer;

}

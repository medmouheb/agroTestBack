package com.agrotech.api.model;


import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.mapping.Document;


@Data
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ProductCategories")
public class ProductCategory {


    @Id
    private String id;

    @NotNull
    @Size(max = 50)
    private String productCategoryType;

    @NotNull
    @Size(max = 50)
    private String productCategoryName;

    @NotNull
    @Size(max = 50)
    private String productCategoryCode;

    private boolean active=true;

    private String notes;
    private String farmer;

}

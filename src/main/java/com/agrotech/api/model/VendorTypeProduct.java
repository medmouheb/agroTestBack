package com.agrotech.api.model;

import com.agrotech.api.dto.VendorTypeProductDto;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;


@Getter
@Setter
@AllArgsConstructor
@Document(collection = "vendorTypeProduct")
public class VendorTypeProduct extends BaseEntity {

    private String VendorSKUCode;

    private String VendorProductName;

    private String ProductCode;

    private String ProductName;

    private String UOMCode;

    private String UOMName;

    @Size(max = 20)
    private String ProductOverride;

    private String ProductType;

    @NotBlank(message = "VendorType VendorUnits is required")
    @Size(max = 9)

    private Double VendorUnits;


    private Double VendorFreeUnits;

    @NotBlank(message = "VendorType TotalsUnits is required")
    @Size(max = 12)
    private Double TotalsUnits;

    private Boolean isDeleted = false;




    }




package com.agrotech.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Setter
@Getter
@NoArgsConstructor
@AllArgsConstructor
public class VendorTypeProductDto extends BaseDto{
    private String farmer;

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

    private Boolean isDeleted=false;

}

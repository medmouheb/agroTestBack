package com.agrotech.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorTypeFreightDto {

    private String farmer;

    private String HaulageVendorCode;

    private String FreightTermCode;

    private String FreightTermName;

    private String FreightType;

    private Boolean isDeleted=false;

}

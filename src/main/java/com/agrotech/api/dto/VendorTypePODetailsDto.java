package com.agrotech.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorTypePODetailsDto extends  BaseDto{


    private String BuyerName;

    private String LocationName;

    private String VendorName;

    private String VendorAddressLine1;

    private String VendorAddressLine2;

    private String VendorAddressLine3;

    private String VendorAddressLine4;

    private String VendorCity;

    private String VendorState;

    private String VendorZip;

    private String VendorCounty;

    private String VendorCountry;

    private String VendorTelephone;

    private String VendorFax;

    private String VendorEmail;

    private String VendorWebsite;

    private Boolean isDeleted=false;
}

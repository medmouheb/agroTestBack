package com.agrotech.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "VendorTypePODetails")
public class VendorTypePODetails extends BaseEntity{

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

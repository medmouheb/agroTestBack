package com.agrotech.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "VendorsShipping")
public class VendorsShipping extends BaseEntity{

    private String ShippingLocationCode;
    private String ShippingLocationName;
    private String ShippingAddressLine01;
    private String ShippingAddressLine02;
    private String ShippingAddressLine03;
    private String ShippingAddressLine04;
    private String ShippingCityNo;
    private String ShippingStateNo;
    private String ShippingPostalCode;
    private String ShippingCountry;
    private String ShippingTelephoneNo;

    private Boolean isDeleted=false;

}

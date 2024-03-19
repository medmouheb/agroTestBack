package com.agrotech.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorsShippingDto extends BaseDto{
    private String farmer;

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

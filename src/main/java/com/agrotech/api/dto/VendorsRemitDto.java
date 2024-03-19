package com.agrotech.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorsRemitDto extends BaseDto{

    private String RemitToName;
    private String RemitToAddressLine01;
    private String RemitToAddressLine02;
    private String RemitToAddressLine03;
    private String RemitToAddressLine04;
    private String RemitToCityNo;
    private String RemitToStateNo;
    private String RemitToPostalCode;
    private String RemitToCountry;
    private String farmer;

    private Boolean isDeleted=false;
}

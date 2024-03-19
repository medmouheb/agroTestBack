package com.agrotech.api.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorsDto extends BaseDto{

    private String farmer;

    @Size(max = 40)
    private String Language;
    @Size(max = 40)

    private String VendorCode;
    @Size(max = 40)

    private String VendorName01;
    @Size(max = 40)

    private String VendorName02;
    @Size(max = 40)

    private String AddressLine01;
    @Size(max = 40)

    private String AddressLine02;
    @Size(max = 40)

    private String AddressLine03;
    @Size(max = 40)

    private String AddressLine04;
    @Size(max = 16)

    private String CityNo;
    @Size(max = 16)

    private String StateNo;
    @Size(max = 40)

    private String PostalCode;
    @Size(max = 16)

    private String Country;
    @Size(max = 40)
    private String TelephoneNo;
    @Size(max = 40)

    private String MobileNo;
    @Size(max = 40)

    private String FaxNo;
    @Size(max = 40)

    private String Email01;
    @Size(max = 40)

    private String Email02;
    @Size(max = 40)

    private String Website;
    @Size(max = 40)

    private String Paysite;
    @Size(max = 4)

    private Number Vendor;
    @Size(max = 4)

    private Number VendorType;

    private Boolean SurveyRequired;
    @Size(max = 200)

    private String Notes;

    private Boolean Active;

    private Boolean isDeleted=false;
}

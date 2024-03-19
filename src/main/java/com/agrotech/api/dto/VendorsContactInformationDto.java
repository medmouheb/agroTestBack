package com.agrotech.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorsContactInformationDto extends BaseDto{
    private String farmer;

    private String Type;
    private String Internal;

    private String External;

    private String Title;

    private String FirstName;

    private String LastName;

    private String JobTitle;

    private String Email;

    private String Primary1;

    private Boolean Active;

    private String CountryCode;

    private String AreaCode;

    private String TelephoneNumber;

    private String Extension;

    private String labelCode;

    private String labelName;

    private String Primary2;

    private String BusinessDetails;

    private String InvoiceVendorCode;

    private String InvoiceVendorName;

    private String PayRate;

    private Boolean isDeleted = false;
}
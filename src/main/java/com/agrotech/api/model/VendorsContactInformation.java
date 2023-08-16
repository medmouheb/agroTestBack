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
@Document(collection = "VendorsContactInformation")
public class VendorsContactInformation extends BaseEntity{

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

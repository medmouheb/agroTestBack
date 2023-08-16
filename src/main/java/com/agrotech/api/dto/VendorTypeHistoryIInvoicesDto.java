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
public class VendorTypeHistoryIInvoicesDto extends BaseDto{


    private String InvoiceType;
    private String InvoiceCode;
    private String VendorCode;
    private String VendorName;
    private String VendorSKUCode;

    private String VendorSKUName;

    private Double Quantity;

    private Double InvoicePrice;

    private Boolean isDeleted=false;



}

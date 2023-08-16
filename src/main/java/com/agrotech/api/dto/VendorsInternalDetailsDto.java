package com.agrotech.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorsInternalDetailsDto extends BaseDto{


    private String CompanyCode;

    private String CompanyName;

    private String CurrencyCode;

    private String CurrencyName;

    private String PaymentTermCode;

    private String PaymentTermName;

    private String PaymentMethodCode;

    private String PaymentMethodName;

    private String TaxExempt;

    private String CashBasis;

    private Boolean isDeleted = false;
}

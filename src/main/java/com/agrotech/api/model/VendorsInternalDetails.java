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
@Document(collection = "VendorsInternalDetails")
public class VendorsInternalDetails extends BaseEntity{

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

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
public class VendorTypePricingDto extends BaseDto{


    private String PriceBasisMode;
    private String MarketCode ;
    private String PaymentTermCode ;
    private String CurrencyCode ;
    private String CurrencyName ;
    @Size(max = 10)
    private Double CurrencyExchangeRate;
    @Size(max = 9)
    private Double VendorUnitPrice;
    @Size(max = 10)
    private Double TotalAddOnPrice;
    private String Tax1;
    private String TariffCode;
    private String Tax2;
    private String TariffName;
    private String ProductType;
    @Size(max = 10)
    private Double Rate ;
    private String AssuranceCode ;
    private String AssuranceName ;
    private String TestingLocation ;
    @Size(max = 10)

    private Double Min;
    @Size(max = 10)

    private Double Max;
    private String SupplementalCharge ;
    private String SupplementCode ;
    private String SupplementName ;
    private String TransactionBasis ;
    private String PaymentType ;
    private String PayeeType ;
    private String TransactionID ;
    private Boolean Taxable ;

    private Boolean isDeleted=false;
}

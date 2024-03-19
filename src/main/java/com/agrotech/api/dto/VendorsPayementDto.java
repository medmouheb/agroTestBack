package com.agrotech.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorsPayementDto extends  BaseDto{
    private String farmer;

    private String Currency;

    private String PaymentTermCode;
    private String PaymentTermName;

    private String Discounts;

    private String FreightTermeCode;
    private String FreightTermeName;

    private String ShipmentCode;
    private String Shipment;

    private String SelfBilling;
    private String ContractVendor;

    private String Country;
    private String FederalTaxID;

    private String StateTaxID;
    private String DefaultPriceMode;

    private String ContractPrice;

    private String POPrice;

    private String MarketPrice;

    private String InvoicePrice;

    private String PaymentMethodCode;

    private String PaymentMethodName;

    private Boolean isDeleted=false;
}

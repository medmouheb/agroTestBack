package com.agrotech.api.model;

import com.agrotech.api.utils.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "VendorsPayement")
public class VendorsPayments extends BaseEntity{

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

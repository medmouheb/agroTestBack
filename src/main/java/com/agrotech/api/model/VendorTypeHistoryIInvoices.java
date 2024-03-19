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
@Document(collection = "vendorTypeHistoryIInvoices")
public class VendorTypeHistoryIInvoices extends BaseEntity{

    private String InvoiceType;
    private String InvoiceCode;
    private String VendorCode;
    private String VendorName;
    private String VendorSKUCode;

    private String VendorSKUName;
    private Double Quantity;

    private Double InvoicePrice;

    private Boolean isDeleted=false;

    private String farmer;

}

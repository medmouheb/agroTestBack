package com.agrotech.api.model;

import com.agrotech.api.utils.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "Reception")
public class Reception extends BaseEntity{


    private Double CostCenterCode ;

    private String VendorCode ;

    private String HaulageVendorCode ;

    private LocalDate TransDate;

    private LocalDate EventDate;

    @Indexed(unique = true)
    private String RefNo;

    private LocalDate TransTime;

    private Boolean Void;

    private String VehiculeCodeNo;

    private String ScaleNo;

    private String ExternalVehicule;

    private String ExternalDriver ;

    @NotBlank(message = ValidationMessages.TYPE_REQUIRED)
    private Double GrossZeroGrossZeroWeightButton;

    private LocalDate GrossZeroWeightDateTime;

    private Number GrossZeroWeightScaleRef;

    private Double GrossButton;

    private LocalTime GrossWtDateTime;

    private Double GrossWtScaleRef;

    @NotBlank(message = ValidationMessages.TYPE_REQUIRED)
    private Double TareZeroWeightButton;

    private String TareZeroWeightDateTime;

    private Number TareZeroWeightScaleRef;

    private String TareTareButton;

    private LocalTime TareWtDateTime;

    private Number TareWtScaleRef;

    private Double Net;


    private String RecordType;

    private String CostCenterName;

    private String VendorName;

    private String HaulageVendorName;
    private String ComplexPoNo;

    private Number ProductNo;

    private String ProductName;

    private String VendorSkuNo;

    private String VendorSkuName;

    private String UnitDescription;

    private Number UnitsPer;

    private Number LotNo;

    private String LocationNo;

    private Number UnitReceived;

    private Double Quantity;

    private Double Amount;

    private Double TotalAddOns ;

    private Double TotalDiscount;

    private Double TotalTax;

    private Double TotalAmount;

    private LocalDate ExpirationDate;

    private String CurrencyCode;


    private Double CurrencyName;

    private String Comments;

    private String ProductCode;

//• Product Name: defaults from Product No and displays a description of the add-on.
    private String ProductName2;

    private String TransactionBasis;

    private String PayementType;

    private String PayeeType;

    private String TransactionEntityId;

    private String PayCalculationType;

    private Double ProductAmout;

    private Double NetAmount;

    private String VendorCalculationAmount;

    private Double Normal;

    private Double Deduction;

    private String Taxable;
// Currency Code: defaults based on the currency assigned to the purchase order as defined, with no option to modify.
    private String CurrencyCode2;
// Currency Name: defaults from Currency No and displays a description of the currency type.

    private String CurrencyName2;
// Amount: is the total amount of tax applicable on the product.
    private Double Amount2;


    private String TaxCodes;

    private String TaxName;
// Amount: is the total amount of tax applicable on the product.
    private Double Amount3;

    private Boolean isDeleted=false;

    private String farmer;

}

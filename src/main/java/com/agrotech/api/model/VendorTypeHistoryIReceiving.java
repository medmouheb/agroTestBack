package com.agrotech.api.model;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalTime;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "vendorTypeHistoryIReceiving")
public class VendorTypeHistoryIReceiving extends BaseEntity{

    private String ApprovalUserID;
    private LocalTime ApprovalDateTime;
    private String ReceivingType;
    private String PurchaseTransCode;

    @Indexed(unique = true)
    @Size(max = 20)
    private String RefCode;
    private LocalTime ReceiveDate;
    private String VendorCode;
    private String VendorSKUCode;
    private String VendorSKUName;
    private String ProductCode;
    private String ProductName;

    @Size(max = 9)
    private Number Quantity;

    @Size(max = 9)
    private Number QuantityFree;

    @Size(max = 9)
    private Number ExtraFree;

    @Size(max = 9)
    private Number RelativeQuantity;

    private Boolean isDeleted=false;

    private String farmer;

}

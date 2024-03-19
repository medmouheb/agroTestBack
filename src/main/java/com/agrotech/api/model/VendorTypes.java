package com.agrotech.api.model;

import jakarta.validation.constraints.NotBlank;
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
@Document(collection = "VendorTypes")
public class VendorTypes extends BaseEntity{

    @NotBlank(message = " type is required")
    @Indexed(unique = true)
    private LocalTime RequisitionDate;
    @NotBlank(message = " type is required")
    @Indexed(unique = true)
    private String PurchaseOrderType;
    @NotBlank(message = " type is required")
    @Indexed(unique = true)
    @Size(max = 10)
    private Number PoCode;
    @NotBlank(message = " type is required")
    @Indexed(unique = true)
    private LocalTime PoDate;

    private String BuyerCode;
    @NotBlank(message = " type is required")
    private String VendorCode;

    private String BrokerCode;

    private String ShippingLocationCode;
    @NotBlank(message = " type is required")
    @Indexed(unique = true)
    @Size(max = 20)
    private String VendorRefCode;

    private Boolean Void;
    @NotBlank(message = " type is required")
    @Indexed(unique = true)
    private LocalTime CreationDate;
    @NotBlank(message = " ype is required")
    @Indexed(unique = true)
    private LocalTime LastModified;
    @NotBlank(message = " type is required")
    @Indexed(unique = true)
    private String Modifier;

    private Boolean isDeleted=false;

    private String farmer;

}

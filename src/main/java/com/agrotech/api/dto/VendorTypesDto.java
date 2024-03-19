package com.agrotech.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalTime;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorTypesDto extends BaseDto{

    private String farmer;

    @NotBlank(message = " ype is required")
    @Indexed(unique = true)
    private LocalTime RequisitionDate;
    @NotBlank(message = " ype is required")
    @Indexed(unique = true)
    private String PurchaseOrderType;
    @NotBlank(message = " ype is required")
    @Indexed(unique = true)
    @Size(max = 10)
    private Number PoCode;
    @NotBlank(message = " ype is required")
    @Indexed(unique = true)
    private LocalTime PoDate;

    private String BuyerCode;
    @NotBlank(message = " ype is required")
    private String VendorCode;

    private String BrokerCode;

    private String ShippingLocationCode;
    @NotBlank(message = " ype is required")
    @Indexed(unique = true)
    @Size(max = 20)
    private String VendorRefCode;

    private Boolean Void;
    @NotBlank(message = " ype is required")
    @Indexed(unique = true)
    private LocalTime CreationDate;
    @NotBlank(message = " ype is required")
    @Indexed(unique = true)
    private LocalTime LastModified;
    @NotBlank(message = " ype is required")
    @Indexed(unique = true)
    private String Modifier;

    private Boolean isDeleted=false;


}

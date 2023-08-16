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
public class VendorTypePurchaserDto extends BaseDto{


    private String DestinationCostCenterCode;

    private String PickupCostCenterCode;

    private String Attachments;


    @Size(max = 200)
    private String Notes;

    private String DestinationCostCenterName;

    private String PickupCostCenterName;

    private String FreightTermName;

    private Boolean isDeleted=false;
}

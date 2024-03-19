package com.agrotech.api.model;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "VendorTypePurchaser")
public class VendorTypePurchaser extends BaseEntity{


    private String DestinationCostCenterCode;

    private String PickupCostCenterCode;

    private String Attachments;


    @Size(max = 200)
    private String Notes;

    private String DestinationCostCenterName;

    private String PickupCostCenterName;

    private String FreightTermName;

    private Boolean isDeleted=false;

    private String farmer;


}

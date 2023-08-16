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
@Document(collection = "vendorTypeFreight")
public class VendorTypeFreight extends BaseEntity{

    private String HaulageVendorCode;

    private String FreightTermCode;

    private String FreightTermName;

    private String FreightType;

    private Boolean isDeleted=false;
}

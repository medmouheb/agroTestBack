package com.agrotech.api.model;


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
@Document(collection = "VendorTypeDetails")
public class VendorTypeDetails extends BaseEntity{



    @Indexed(unique = true)
    @NotBlank(message = "Vendor Type Code is required")
    @Size(max = 3)
    private Number OrderLine;
    private String ContractCode ;

    private String FacilityCode;

    private String FacilityType;
    private String FacilityName;

    private String PriceMode;

    private String farmer;

    private Boolean isDeleted=false;
}

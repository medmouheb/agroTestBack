package com.agrotech.api.model;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "VendorTypeFeed")
public class VendorTypeFeed extends BaseEntity{

    private String POState;

    @Size(max = 9)
    private Double BasisPrice;

    private Boolean isDeleted=false;
    private String farmer;


}

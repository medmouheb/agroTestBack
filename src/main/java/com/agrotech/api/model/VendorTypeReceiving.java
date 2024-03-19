package com.agrotech.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@Document(collection = "VendorTypeReceiving")
public class VendorTypeReceiving extends BaseEntity{

    private Boolean MultipleReceivings;
    

    private Boolean IsDeleted=false;
    private String farmer;


}

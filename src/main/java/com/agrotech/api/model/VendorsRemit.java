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
@Document(collection = "VendorsRemit")
public class VendorsRemit {

    private String RemitToName;
    private String RemitToAddressLine01;
    private String RemitToAddressLine02;
    private String RemitToAddressLine03;
    private String RemitToAddressLine04;
    private String RemitToCityNo;
    private String RemitToStateNo;
    private String RemitToPostalCode;
    private String RemitToCountry;

    private Boolean isDeleted=false;
}

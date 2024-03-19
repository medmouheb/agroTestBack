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
@Document(collection = "facility")
public class FacilityDetails extends BaseEntity{

    private String farmer;

    private String facilityID;

    private String facilityName;

    private String primary;

    private String alternative;

    private Boolean isDeleted=false;




}

package com.agrotech.api.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.agrotech.api.utils.ValidationMessages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "costCenter ")
public class CostCenter extends BaseEntity {
	
    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Indexed(unique = true)
	private String code ; 
    @NotBlank(message = ValidationMessages.NAME_REQUIRED)
    @Size(max = 50, message = ValidationMessages.NAME_TOO_LONG)
	private String name ;


    @NotBlank(message = ValidationMessages.NAME_REQUIRED)
    @Size(max = 50, message = ValidationMessages.NAME_TOO_LONG)
    private String typecost ;

    private Boolean isDeleted=false;

    @Size(max = 5)
    private String farmType;
    @Size(max = 250)

    private String  division_Name;
    @Size(max = 10)
    private String  division_Code;
    @Size(max = 10)
    private String  facilityType;
    @Size(max = 10)
    private String  speciesType;

    private String divisiontype;
    private String farmer;

}

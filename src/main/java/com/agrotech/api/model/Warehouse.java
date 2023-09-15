package com.agrotech.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.agrotech.api.enums.CostCenterType;
import com.agrotech.api.utils.ValidationMessages;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "warehouse")
public class Warehouse extends BaseEntity {
   
	@NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Indexed(unique = true)
    @Size(max = 50, message = ValidationMessages.CODE_TOO_LONG)
    private String code;
	@NotBlank(message = ValidationMessages.NAME_REQUIRED)
    @Indexed(unique = true)
    @Size(max = 250, message = ValidationMessages.NAME_TOO_LONG)    
	private String name;
//	@NotBlank(message = ValidationMessages.TYPE_REQUIRED)
    private String type;
    private String facilityType;
    @Size(max = 50)
    private LocalDate startingDate;
    private Boolean isPrimary;
    @Size(max = 500)
    private String address1;
    @Size(max = 500)
    private String address2;
    @Size(max = 10)
    private String cityCode;
    @Size(max = 100)
    private String cityName;
    @Size(max = 3)
    private String wilayaCode;
    @Size(max = 100)
    private String wilayaName;
    @Size(max = 10)
    private String zipCode;
    @Size(max = 150)
    private String email;
    @Size(max = 12)
    private String phoneNumber;
    @Size(max = 12)
    private String faxNumber;
    @Size(max = 250)
    private double latitude;
    @Size(max = 250)
    private double longitude;
    private Boolean isDeleted=false;


    private String costCenterName;
    private String costCenterCode;
    private String vendor;


    // @NotBlank(message = "Cost center code is required")
    // private String costCenterCode;
    // @NotBlank(message = "Cost center name is required")
    // private String costCenterName;
    //  private String divisionCode;
    //  private String divisionName;
    //  private String vendor;
    //Active
    

}

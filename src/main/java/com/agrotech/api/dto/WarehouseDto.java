package com.agrotech.api.dto;

import com.agrotech.api.model.Fournisseur;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import java.time.LocalDate;

import com.agrotech.api.enums.CostCenterType;
import org.springframework.data.mongodb.core.mapping.DBRef;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDto extends BaseDto {
    @NotBlank(message = "Code is required")
    private String code;
    @NotBlank(message = "Name is required")
    private String name;
    private String type;


    private LocalDate startingDate;
    private Boolean isPrimary;

    private String address;
    //

    private String cityCode;

    private String cityName;

    private String wilayaCode;

    private String wilayaName;

    private String zipCode;

    private String email;

    private String phoneNumber;

    private String faxNumber;

    private double latitude;

    private double longitude;
    private Boolean isDeleted=false;

    private String vendor;

    private String costCenterCode;

    private String costCenterName;
}

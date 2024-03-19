package com.agrotech.api.dto;

import jakarta.validation.constraints.NotBlank;
import lombok.*;

import java.time.LocalDate;


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
    private String vendor;
    private String costCenterCode;
    private String costCenterName;
    private LocalDate startingDate;
    private Boolean isPrimary;
    private String address1;
    private String address2;
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
    private String farmer;

}

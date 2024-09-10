package com.agrotech.api.dto;

import com.agrotech.api.utils.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class WarehouseDto extends BaseDto {
    private String code;
    private String name;
    private String type;
    private Boolean isPrimary;
    private Boolean isOur;
    private String address;
    private String cityCode;
    private String cityName;
    private String state;
    private String country;
    private double latitude;
    private double longitude;
    private double totalCapacity;
    private double currentUtilization;
    private String email;
    private String phoneNumber;
    private String faxNumber;
    private Boolean isDeleted=false;
    private String costCenter;
    private String vendor;
    private String farmer;
}

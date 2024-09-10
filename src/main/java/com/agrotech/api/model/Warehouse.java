package com.agrotech.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
    private String type;

    private Boolean isPrimary;
    private Boolean isOur;
    @Size(max = 500)
    private String address;
    @Size(max = 10)
    private String cityCode;
    @Size(max = 100)
    private String cityName;

    @Size(max = 100)
    private String state;
    @Size(max = 100)
    private String country;
    @Size(max = 250)
    private double latitude;
    @Size(max = 250)
    private double longitude;
    @Size(max = 250)
    private double totalCapacity;
    @Size(max = 250)
    private double currentUtilization;
    @Size(max = 150)
    private String email;
    @Size(max = 12)
    private String phoneNumber;
    @Size(max = 12)
    private String faxNumber;

    private Boolean isDeleted=false;


    private String costCenter;

    private String vendor;

    private String farmer;


}

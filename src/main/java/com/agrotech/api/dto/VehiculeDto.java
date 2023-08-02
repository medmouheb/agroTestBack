package com.agrotech.api.dto;

import com.agrotech.api.utils.ValidationMessages;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VehiculeDto extends  BaseDto {

    private String vehiculeType;
    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Indexed(unique = true)
    @Size(max = 50, message = ValidationMessages.CODE_TOO_LONG)
    private String vehiculeCode;
    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Size(max = 50, message = ValidationMessages.CODE_TOO_LONG)
    private String vehiculeName ;
    @NotBlank(message = ValidationMessages.CODE_REQUIRED)

    private String facilitytype ;
    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    private String measurementType ;

    private Boolean active;
    private Boolean yardBuggy;
    private Boolean external;


    private String bioSecurityLevelCode;
    private Double tareVariance;
    private Double tareWeight;

    private LocalDate tareWtRecord;

    private String tareWtRecordRef;

    private Number Bins;
    private Boolean trailer;
    private Number trailerSequence;
    private Double capacity;
    private String color;
    @Size(max = 200, message = ValidationMessages.CODE_TOO_LONG)
    private String notes;


    private Boolean isDeleted=false;


}

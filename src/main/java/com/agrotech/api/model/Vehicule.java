package com.agrotech.api.model;

import com.agrotech.api.utils.ValidationMessages;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.hibernate.validator.constraints.NotBlank;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "vehicule")
public class Vehicule extends BaseEntity {

    private String vehiculeType;


    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Indexed(unique = true)
    @Size(max = 50, message = ValidationMessages.CODE_TOO_LONG)
    private String vehiculeCode;
    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Size(max = 50, message = ValidationMessages.CODE_TOO_LONG)
    private String vehiculeName;
    @NotBlank(message = ValidationMessages.CODE_REQUIRED)

    private String facilitytype;
    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    private String measurementType;

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

    private String facilityID;

    private String facilityName;

    private String primary;

    private String alternative;

    private Number bin;

    private Double capacity2;


    private Boolean isDeleted = false;

    private String farmer;

}

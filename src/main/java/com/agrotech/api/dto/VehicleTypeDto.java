package com.agrotech.api.dto;


import com.agrotech.api.model.BaseEntity;
import org.springframework.data.mongodb.core.index.Indexed;

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
public class VehicleTypeDto extends BaseDto {

    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Indexed(unique = true)
    @Size(max = 50, message = ValidationMessages.NAME_TOO_LONG)
    private String vehicleTypeCode;

    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Size(max = 50, message = ValidationMessages.NAME_TOO_LONG)
    private String vehicleTypeName;

    private String productType;

    private Boolean active=false;

    private Double unitCost;

    private Double tareWeight;

    private  Number weightCapacity;

    private Boolean isDeleted=false;


    @Override
    public String toString() {
        return "VehicleTypeDto{" +
                "vehicleTypeCode='" + vehicleTypeCode + '\'' +
                ", vehicleTypeName='" + vehicleTypeName + '\'' +
                ", productType='" + productType + '\'' +
                ", active=" + active +
                ", unitCost=" + unitCost +
                ", tareWeight=" + tareWeight +
                ", weightCapacity=" + weightCapacity +
                ", isDeleted=" + isDeleted +
                '}';
    }
}

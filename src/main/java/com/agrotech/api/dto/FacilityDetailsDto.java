package com.agrotech.api.dto;

import com.agrotech.api.utils.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FacilityDetailsDto extends BaseDto {

    private String facilityID;

    private String facilityName;

    private String primary;

    private String alternative;

    private Boolean isDeleted=false;

}

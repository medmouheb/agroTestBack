package com.agrotech.api.dto;

import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorTypeFeedDto extends BaseDto{


    private String POState;

    @Size(max = 9)
    private Double BasisPrice;

    private Boolean isDeleted=false;

}

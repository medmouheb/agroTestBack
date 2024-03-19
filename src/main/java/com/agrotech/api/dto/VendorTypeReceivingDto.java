package com.agrotech.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class VendorTypeReceivingDto extends BaseDto{
    private String farmer;

    private Boolean MultipleReceivings;

    private Boolean IsDeleted=false;

}

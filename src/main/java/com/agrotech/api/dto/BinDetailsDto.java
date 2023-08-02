package com.agrotech.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class BinDetailsDto extends BaseDto {

    private Number bin;

    private Double capacity;

    private Boolean isDeleted=false;


}

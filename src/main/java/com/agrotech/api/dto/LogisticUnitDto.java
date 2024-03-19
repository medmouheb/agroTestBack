package com.agrotech.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class LogisticUnitDto extends BaseDto{

    @Indexed(unique = true)
    private String logisticCode;

    @Indexed(unique = true)

    private String logisticName;

    private String companyName;

    private String divisionName;

    private Boolean isDeleted=false;


    private String farmer;

}

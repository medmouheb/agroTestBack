package com.agrotech.api.dto;

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
public class BrokersDto extends BaseDto{


    @NotBlank(message = "BrokerCode is required")
    @Size(max = 50)
    @Indexed(unique = true)
    private  String BrokerCode;

    @NotBlank(message = "BrokerCode is required")
    @Size(max = 50)
    private  String BrokerName;

    private String DivisionCode;

    private String DivisionName;

    private Boolean Active;

    @Size(max = 100)
    private String Notes;

    private Boolean isDeleted=false;

}

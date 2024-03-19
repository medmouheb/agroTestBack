package com.agrotech.api.dto;


import com.agrotech.api.utils.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class StockDTO extends BaseDto {
    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Indexed(unique = true)
    private String code ;
    private String farmer;

    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Indexed(unique = true)
    private String name ;

    private String notes ;

    private String typeOfMovement ;

    private String product;

    private String warehouse;

    private Boolean isDeleted=false;

}

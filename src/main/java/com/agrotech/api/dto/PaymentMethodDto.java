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
public class PaymentMethodDto extends  BaseDto  {
    @NotBlank()
    @Indexed(unique = true)
    @Size(max = 50)
    private String code ;
    @NotBlank()
    @Size(max = 250)
    private String name ;

    private String notes;
    private Boolean active ;


    private String farmer;

    private Boolean isDeleted=false;
}

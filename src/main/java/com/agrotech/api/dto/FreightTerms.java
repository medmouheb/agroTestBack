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
public class FreightTerms extends  BaseDto {
    @Indexed(unique = true)
    @NotBlank(message = ValidationMessages.TYPE_REQUIRED)
    private String  freighttermcode ;

}

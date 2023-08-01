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
public class DeliveryInstructionDto extends  BaseDto{
    @Indexed(unique = true)
    @NotBlank(message = ValidationMessages.TYPE_REQUIRED)
    private String  productType ;
    @Size(max = 50)
    private String instructiuonName;
    @Indexed(unique = true)
    @Size(max = 5)
    private String instructiuonCode;
    private Boolean isDeleted=false;
    @Size(max = 200)

    private String  notes;
    private Boolean active;
}

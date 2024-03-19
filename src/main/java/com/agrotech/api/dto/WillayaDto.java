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
public class WillayaDto extends BaseDto {

    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Indexed(unique = true)
    @Size(max = 10, message = ValidationMessages.CODE_TOO_LONG)
    private String code ;
    @NotBlank(message = ValidationMessages.NAME_REQUIRED)
    @Indexed(unique = true)
    @Size(max = 10, message = ValidationMessages.NAME_TOO_LONG)
    private String name ;
    private Boolean isDeleted=false;
    private String farmer;

}

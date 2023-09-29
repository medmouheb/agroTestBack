package com.agrotech.api.dto;

import java.util.HashSet;
import java.util.Set;

import com.agrotech.api.utils.ValidationMessages;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.agrotech.api.enums.statusVente;
import com.agrotech.api.model.SalesSKU;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesDto extends BaseDto {

    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Indexed(unique = true)
    @Size(max = 50, message = ValidationMessages.CODE_TOO_LONG)
    private String code ;
    @NotBlank(message = ValidationMessages.NAME_REQUIRED)
    @Size(max = 250, message = ValidationMessages.NAME_TOO_LONG)
    private String name;
    private String type ;
    private String currency ;
    private String Payment_Term ;
    private Boolean isDeleted=false;

	

}

package com.agrotech.api.dto;


import com.agrotech.api.model.Produit;
import com.agrotech.api.utils.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

import java.util.HashMap;
import java.util.Map;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class CropDTO extends BaseDto {
    @NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Indexed(unique = true)
    private String code ;

    @NotBlank(message = ValidationMessages.NAME_REQUIRED)
    @Size(max = 250, message = ValidationMessages.NAME_TOO_LONG)
    private String name="" ;

    private Produit product ;

    private String latitude="" ;
    private String longitude="" ;

    private float width=0;
    private float height=0;

    private Map<String, String> solutions  = new HashMap<>();

    private String healthStatus="";

    private Boolean isDeleted=false;

}

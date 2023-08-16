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
public class INCOTermsDto extends BaseDto{


    private String TransportType;

    @NotBlank(message = "INCOTermCode is required")
    @Indexed(unique = true)
    @Size(max = 50)
    private String INCOTermCode;


    @Size(max = 50)
    private String INCOTermName;



    private Boolean Active;


    @Size(max = 200)
    private String Notes;


    private Boolean isDeleted=false;

}

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
public class FreightTermsDto extends  BaseDto {
    @Indexed(unique = true)
    @NotBlank(message = ValidationMessages.TYPE_REQUIRED)
    @Size(max = 50)
    private String  freighttermcode ;
    private String farmer;

    @NotBlank(message = ValidationMessages.TYPE_REQUIRED)
    @Size(max = 50)
    private String  freighttermname ;

    private Boolean Active;

    @Size(max = 200)
    private String notes;

    private Boolean isDeleted=false;

    @Override
    public String toString() {
        return "FreightTermsDto{" +
                "freighttermcode='" + freighttermcode + '\'' +
                ", freighttermname='" + freighttermname + '\'' +
                ", Active=" + Active +
                ", notes='" + notes + '\'' +
                '}';
    }
}

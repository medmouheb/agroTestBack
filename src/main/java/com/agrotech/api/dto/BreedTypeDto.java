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
public class BreedTypeDto extends BaseDto{

    private String ProductType;
    @NotBlank(message = ValidationMessages.TYPE_REQUIRED)
    @Indexed(unique = true)
    @Size(max=50)
    private String BreedTypeCode;
    @NotBlank(message = ValidationMessages.TYPE_REQUIRED)
    @Size(max=50)
    private String BreedTypeName;

    private Boolean Active;
    @Size(max=200)
    private String Notes;
    private Boolean isDeleted=false;
    private String farmer;


    @Override
    public String toString() {
        return "BreedTypeDto{" +
                "ProductType='" + ProductType + '\'' +
                ", BreedTypeCode='" + BreedTypeCode + '\'' +
                ", BreedTypeName='" + BreedTypeName + '\'' +
                ", Active=" + Active +
                ", Notes='" + Notes + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}

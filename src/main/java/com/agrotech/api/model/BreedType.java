package com.agrotech.api.model;

import com.agrotech.api.utils.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "BreedType")
public class BreedType extends BaseEntity{

    private String ProductType;
    @NotBlank(message = ValidationMessages.TYPE_REQUIRED)
    @Indexed(unique = true)
    @Size(max=50)
    private String BreedTypeCode;
    @NotBlank(message = ValidationMessages.TYPE_REQUIRED)
    @Size(max=50)
    private String BreedTypeName;

    private Boolean Active=false;
    @Size(max=200)
    private String Notes;

    private Boolean isDeleted=false;



}

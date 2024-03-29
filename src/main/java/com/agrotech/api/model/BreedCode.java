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
@Document(collection = "BreedCode")
public class BreedCode extends BaseEntity{

    private String ProductType;
    private String SpeciesType;
    @Size(max=50)
    @Indexed(unique = true)
    @NotBlank(message = ValidationMessages.TYPE_REQUIRED)
    private String BreedCode;
    @NotBlank(message = ValidationMessages.TYPE_REQUIRED)
    @Size(max=50)
    private String BreedCodeName;

    private Boolean primarySex;
    private String BreedTypeCode;
    private Boolean Placement;

    private Number MaleRatio;
    private Number POL;
    private Number Saleability;

    private String EggAllocation;
    private Boolean Active;
    private String Notes;


    private Boolean isDeleted=false;
    private String farmer;

}

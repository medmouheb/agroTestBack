package com.agrotech.api.model;


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
@Document(collection = "potentialClient")
public class PotentialClient extends BaseEntity{


    @NotBlank(message = "BuyerCode is required")
    @Indexed(unique = true)
    @Size(max = 50)
    private String code;
    @Indexed(unique = true)
    @NotBlank(message = "BuyerName is required")

    @Size(max = 50)
    private String name;

    private String farmer;

    private String email;
    private String phone1;
    private String phone2;
    private String firstName;
    private String lastName;
    private String notes;
    private boolean exemptionTax;
    private String adresse;
    private String ville;
    private String codePostal;
    private String pays;
    private String etat;



    private Boolean isDeleted=false;

}

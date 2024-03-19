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
public class PotentialClientDto  extends BaseDto{



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

    @Override
    public String toString() {
        return "PotentialClientDto{" +
                "code='" + code + '\'' +
                ", name='" + name + '\'' +
                ", farmer='" + farmer + '\'' +
                ", email='" + email + '\'' +
                ", phone1='" + phone1 + '\'' +
                ", phone2='" + phone2 + '\'' +
                ", firstName='" + firstName + '\'' +
                ", lastName='" + lastName + '\'' +
                ", notes='" + notes + '\'' +
                ", exemptionTax=" + exemptionTax +
                ", adresse='" + adresse + '\'' +
                ", ville='" + ville + '\'' +
                ", codePostal='" + codePostal + '\'' +
                ", pays='" + pays + '\'' +
                ", etat='" + etat + '\'' +
                ", isDeleted=" + isDeleted +
                '}';
    }
}

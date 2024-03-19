package com.agrotech.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class UtilisationDuProduitDto extends BaseDto{
    private String farmer;

    private LocalDate dateDeTransposition;

    private String codeEntrepot;

    private Number unitesSaisies;

    private String transCode;

    private String typeDeProduit;

    private String codeProduit;

    private String nomDuProduit;

    private String numeroDeLot;

    private String location;

    private Number quantite;

    private Double montant;

    private String nDeReference;

    private String commentaire;

    private Boolean Void;

    private Boolean isDeleted=false;
}

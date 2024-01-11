package com.agrotech.api.dto;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class InventaireInitialDto extends BaseDto{


    @Id
    private String id;

    private String codeDeTransaction;

    private String typeDeProduit;

    private String codeProduit;

    private String nomDuProduit;

    private Double uniteDinventaire;

    private Double  prixUnitaire;

    private Double price;

    @Indexed(unique = true)
    private Number codeDeLot;

    private Double codeDeLocalisation;

    @Indexed(unique = true)
    private Double codeDeReference;

    private LocalDate dateDeEvenement;

    private LocalDate dateExpiration;

    private String temps;

    private String commentaire;

    private Boolean Vide;

    private Boolean isDeleted=false;

}

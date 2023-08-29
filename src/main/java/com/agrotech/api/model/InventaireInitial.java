package com.agrotech.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.annotation.Id;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import java.time.LocalDate;
import java.time.LocalTime;


@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "InventaireInitial")
public class InventaireInitial {

    @Id
    private String id;

    private String codeDeTransaction;

    private String typeDeProduit;

    private String codeProduit;

    private String nomDuProduit;

    private String uniteDinventaire;

    private Double  prixUnitaire;

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

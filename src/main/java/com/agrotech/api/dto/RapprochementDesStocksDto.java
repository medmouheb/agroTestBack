package com.agrotech.api.dto;


import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.index.Indexed;

import java.time.LocalDate;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class RapprochementDesStocksDto extends BaseDto{

    private String typeEnregistrement;
    private String farmer;

    private String transCode;

    private LocalDate dateInventaire;

    @Indexed(unique = true)
    private String nDeReference;

    private String iDEntiteDeTransaction;

    private String nomEntiteDeTransaction;

    private String numeroDeProduit;

    private String nomDuProduit;

    private String typeDeProduit;

    private String numeroDeLot;

    private String numeroEmplacement;

    private Double quantite;

    private String leValeur;

    private String statusDeLaPublication;

    private String codeSource;

    private LocalDate dateDeDebut;

    private LocalDate dateDeDebutDeReception;

    private Number quantiteDeDepart;

    private Number valeurUnitaireDeDepart;

    private Number valeurDeDepart;

    private String drapeauFerme;

    private String consignationComplexeBondecommandeNon;

    private String drapeaudeconsignation;

    private String complexeDeTransfertDeConsignationBonDeCommandeN;

    private Number numeroDeReferenceDuTransfertDeConsignation;

    private String nomDuCentreDeCouts;

    private Number nDuCentreDeCouts;

    private LocalDate dateDeCreation;

    private LocalDate dateDeFinDeReception;

    private LocalDate dateDExpiration;

    private String typeInstallationDuCentreDeCoutsExterne;

    private String nomDuCentreDeCoutsExterne;

    private Number nDuCentreDeCoutsExterne;

    private String nomDeLaFerme;

    private Number nDeLaFerme;

    private String typeDeFerme;

    private String dateDinactivite;

    private String IRN;

    private Number nombreDeReceptions;

    private String baseDeRemuneration;

    private Number termeDePaiementN;

    private String termedePaiementNom;

    private Number numeroDeLigneDeCommande;

    private String bonDecommandeNon;

    private String commandeAchatcomplexeNon;

    private String noDeReferenceAchat;

    private Double quantiteRestante;

    private Double valeurUnitaireRestante;

    private Number numeroDeReferenceDuFournisseur;

    private Double quantiteDeDebutDeFiletage;

    private Double valeurUniteDeDebutDeThread;

    private Double valeurDeDebutDeThread;

    private LocalDate dateDeCreationDuFil;

    private LocalDate dateDuFil;

    private Double quantiteDeFil;

    private String typeEnregistrementDeThread;

    private Number nDeReferenceDuFiletage;

    private Double quantiteRestanteDeFil;

    private Double valeurUnitaireRestanteDuFil;

    private Double valeurRestanteDuFil;

    private String codeSourceDuFil;

    private String codeDeTransmissionDeFil;

    private Double valeurUniteDeFiletage;

    private Double valeurDeFil;

    private Double quantiteDeTransport;

    private Double valeurUnitaireTrans;

    private Double valeurTrans;

    private String emplacementDeDestinationDuTransfertNon;

    private Number numeroDeLotDeDestinationDuTransfert;

    private Number nDuProduitDeDestinationDuTransfert;

    private String nomDuProduitDeDestinationDuTransfert;

    private Number numeroDeReferenceDeLaDestinationDuTransfert;

    private String idEntiteDeTransactionDeDestinationDeTransfert;

    private String nomDeEntiteDeLaTransactionDeDestinationDuTransfert;

    private String typeEntrepotDeDestinationDuTransfert;

    private String emplacementDeLaSourceDeTransfertNon;

    private Number numeroDeLotSourceDeTransfert;

    private Number nDuProduitSourceDeTransfert;

    private String nomDuProduitSourceDeTransfert;

    private String typeEntrepotOrigineDuTransfert;

    private String descriptionDeUnite;

    private Double valeurUnitaire;

    private String unitesPar;

    private Number nDuVendeur;

    private String nomDuVendeur;

    private Number noSKUDuFournisseur;

    private String nomSKUDuFournisseur;

    private String typeEntrepot;

    private Boolean isDeleted=false;



}

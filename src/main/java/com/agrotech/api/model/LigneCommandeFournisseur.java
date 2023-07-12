package com.agrotech.api.model;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.agrotech.api.utils.ValidationMessages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.math.BigDecimal;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ligne_commande_fournisseur")
public class LigneCommandeFournisseur extends BaseEntity {
   

	  @NotBlank(message = ValidationMessages.QUANTITE_REQUIRED)
	  private BigDecimal quantite;
	  @NotBlank(message = ValidationMessages.PRIX_UNITAIRE_REQUIRED)
	  private BigDecimal prixUnitaire;

       @DBRef
       private Produit produits ;
       @DBRef
	   private CommandeFournisseur commandeFournisseur;
}

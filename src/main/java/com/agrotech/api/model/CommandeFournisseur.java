package com.agrotech.api.model;

import java.time.Instant;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.agrotech.api.enums.EtatCommande;
import com.agrotech.api.utils.ValidationMessages;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "commande_Fournisseur")
public class CommandeFournisseur extends BaseEntity {
	
	  @NotBlank(message = ValidationMessages.CODE_REQUIRED)
      @Indexed(unique = true)	  
	  private String code;
	  @NotBlank(message = ValidationMessages.DATE_COMMANDE_REQUIRED)
	  private Instant dateCommande;
	  @NotBlank(message = ValidationMessages.ETAT_COMMANDE_REQUIRED)
	  private EtatCommande etatCommande;
	  
	  
	   
	  @DBRef
	  private Fournisseur fournisseur;
	  @DBRef
      private Set<LigneCommandeFournisseur> ligneCommandeFournisseurs = new HashSet<>();
}

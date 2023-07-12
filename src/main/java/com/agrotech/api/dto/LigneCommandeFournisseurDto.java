package com.agrotech.api.dto;

import java.math.BigDecimal;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.agrotech.api.model.CommandeFournisseur;
import com.agrotech.api.model.Produit;
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
public class LigneCommandeFournisseurDto extends BaseDto {

	@NotBlank(message = ValidationMessages.QUANTITE_REQUIRED)
	private BigDecimal quantite;
	@NotBlank(message = ValidationMessages.PRIX_UNITAIRE_REQUIRED)
	private BigDecimal prixUnitaire;

	@DBRef
	private ProduitDto produits;
	@DBRef
	private CommandeFournisseurDto commandeFournisseur;
}

package com.agrotech.api.dto;

import java.time.Instant;
import java.util.HashSet;
import java.util.Set;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

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
public class CommandeFournisseurDto extends BaseDto {

	@NotBlank(message = ValidationMessages.CODE_REQUIRED)
	@Indexed(unique = true)
	private String code;
	@NotBlank(message = ValidationMessages.DATE_COMMANDE_REQUIRED)
	private Instant dateCommande;
	@NotBlank(message = ValidationMessages.ETAT_COMMANDE_REQUIRED)
	private EtatCommande etatCommande;
	@DBRef
	private FournisseurDto fournisseur;
	@DBRef
	private Set<LigneCommandeFournisseurDto> ligneCommandeFournisseurs = new HashSet<>();
	private String farmer;

	public boolean isCommandeLivree() {
		return EtatCommande.LIVREE.equals(this.etatCommande);
	}
}

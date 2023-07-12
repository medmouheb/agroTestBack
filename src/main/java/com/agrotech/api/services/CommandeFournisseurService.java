package com.agrotech.api.services;


import java.math.BigDecimal;
import java.util.List;

import com.agrotech.api.dto.CommandeFournisseurDto;
import com.agrotech.api.dto.LigneCommandeFournisseurDto;
import com.agrotech.api.enums.EtatCommande;
import com.agrotech.api.exceptions.NotFoundException;

public interface CommandeFournisseurService extends BaseService<CommandeFournisseurDto, String> {
	
	  CommandeFournisseurDto findByCode(String code) throws NotFoundException;
	
	  CommandeFournisseurDto updateEtatCommande(String idCommande, EtatCommande etatCommande) throws NotFoundException;

	  CommandeFournisseurDto updateFournisseur(String idCommande, String idFournisseur) throws NotFoundException;
	  
	  CommandeFournisseurDto updateProduit(String idCommande, String idLigneCommande, String idProduit) throws NotFoundException;
	  
	  CommandeFournisseurDto updateQuantiteCommande(String idCommande, String idLigneCommande, BigDecimal quantite) throws NotFoundException;

	  List<LigneCommandeFournisseurDto> findAllLignesCommandesFournisseurByCommandeFournisseurId(String idCommande);

	  // Delete produit ==> delete LigneCommandeFournisseur
	  CommandeFournisseurDto deleteProduit(String idCommande, String idLigneCommande)throws NotFoundException;
	  


}

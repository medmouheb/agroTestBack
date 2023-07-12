
package com.agrotech.api.services.impl;

import java.math.BigDecimal;
import java.util.List;
import java.util.Optional;
import java.util.stream.Collectors;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.domain.Page;
import org.springframework.stereotype.Service;
import org.springframework.util.StringUtils;

import com.agrotech.api.Repository.CommandeFournisseurRepository;
import com.agrotech.api.Repository.FournisseurRepository;
import com.agrotech.api.Repository.LigneCommandeFournisseurRepository;
import com.agrotech.api.Repository.produitRepository;
import com.agrotech.api.dto.CommandeFournisseurDto;
import com.agrotech.api.dto.LigneCommandeFournisseurDto;
import com.agrotech.api.enums.EtatCommande;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.mapper.CommandeFournisseurMapper;
import com.agrotech.api.mapper.FournisseurMapper;
import com.agrotech.api.mapper.LigneCommandeFournisseurMapper;
import com.agrotech.api.model.CommandeFournisseur;
import com.agrotech.api.model.Fournisseur;
import com.agrotech.api.model.LigneCommandeFournisseur;
import com.agrotech.api.model.Produit;
import com.agrotech.api.services.CommandeFournisseurService;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
public class CommandeFournisseurServiceImpl implements CommandeFournisseurService {
	@Autowired
	private CommandeFournisseurRepository commandeFournisseurRepository;
	@Autowired
	private CommandeFournisseurMapper commandeFournisseurMapper;
	@Autowired
	private FournisseurRepository fournisseurRepository;
	@Autowired
	private FournisseurMapper fournisseurMapper;
	@Autowired
	private LigneCommandeFournisseurRepository ligneCommandeFournisseurRepository;
	@Autowired
	private LigneCommandeFournisseurMapper ligneCommandeFournisseurMapper;
	@Autowired
	private produitRepository produitRepository;
	

	public CommandeFournisseur save(CommandeFournisseur entity) {
		return commandeFournisseurRepository.save(entity);
	}

	@Override
	public CommandeFournisseurDto create(CommandeFournisseurDto dto) {
		return commandeFournisseurMapper.toDto(save(commandeFournisseurMapper.toEntity(dto)));
	}

	@Override
	public CommandeFournisseurDto update(String id, CommandeFournisseurDto dto) throws NotFoundException {
		Optional<CommandeFournisseur> cfOptional = commandeFournisseurRepository.findById(id);
		if (cfOptional.isEmpty()) {
			throw new NotFoundException("commande Fournisseur not found ");
		}

		CommandeFournisseur commandeFournisseur = cfOptional.get();
		commandeFournisseurMapper.partialUpdate(commandeFournisseur, dto);

		return commandeFournisseurMapper.toDto(save(commandeFournisseur));
	}

	@Override
	public CommandeFournisseurDto findById(String id) throws NotFoundException {
		Optional<CommandeFournisseur> cfOptional = commandeFournisseurRepository.findById(id);
		if (cfOptional.isEmpty()) {
			throw new NotFoundException("commande Fournisseur not found ");
		}
		return commandeFournisseurMapper.toDto(cfOptional.get());
	}

	@Override
	public List<CommandeFournisseurDto> findAll() {
		return commandeFournisseurRepository.findAll().stream().map(commandeFournisseurMapper::toDto)
				.collect(Collectors.toList());
	}

	@Override
	public Page<CommandeFournisseurDto> findPage(int pageSize, int pageNumber, String filter) {
		return null;
	}

	@Override
	public void delete(String id) throws NotFoundException {
		if (!commandeFournisseurRepository.existsById(id)) {
			throw new NotFoundException("commande Fournisseur not found ");
		}

		commandeFournisseurRepository.deleteById(id);
	}

	@Override
	public CommandeFournisseurDto findByCode(String code) throws NotFoundException {
		Optional<CommandeFournisseur> cfOptional = commandeFournisseurRepository.findCommandeFournisseurByCode(code);
		if (cfOptional.isEmpty()) {
			throw new NotFoundException("Category not found ");
		}
		return commandeFournisseurMapper.toDto(cfOptional.get());
	}

	@Override
	public CommandeFournisseurDto updateEtatCommande(String idCommande, EtatCommande etatCommande)
			throws NotFoundException {
		checkIdCommande(idCommande);
		if (!StringUtils.hasLength(String.valueOf(etatCommande))) {
			log.error("L'etat de la commande fournisseur is NULL");
			throw new NotFoundException("Impossible de modifier l'etat de la commande avec un etat null");
		}
		CommandeFournisseurDto commandeFournisseur = checkEtatCommande(idCommande);
		commandeFournisseur.setEtatCommande(etatCommande);
		CommandeFournisseur savedCommande = commandeFournisseurRepository
				.save(commandeFournisseurMapper.toEntity(commandeFournisseur));
		return commandeFournisseurMapper.toDto(savedCommande);

	}

	@Override
	public CommandeFournisseurDto updateFournisseur(String idCommande, String idFournisseur) throws NotFoundException {
		checkIdCommande(idCommande);
		if (idFournisseur == null) {
			log.error("L'ID du fournisseur is NULL");
			throw new NotFoundException("Impossible de modifier l'etat de la commande avec un ID fournisseur null");
		}
		CommandeFournisseurDto commandeFournisseur = checkEtatCommande(idCommande);
		Optional<Fournisseur> fournisseurOptional = fournisseurRepository.findById(idFournisseur);
		if (fournisseurOptional.isEmpty()) {
			throw new NotFoundException("Aucun fournisseur n'a ete trouve avec l'ID ");
		}
		commandeFournisseur.setFournisseur(fournisseurMapper.toDto(fournisseurOptional.get()));

		return commandeFournisseurMapper
				.toDto(commandeFournisseurRepository.save(commandeFournisseurMapper.toEntity(commandeFournisseur)));
	}

	@Override
	public List<LigneCommandeFournisseurDto> findAllLignesCommandesFournisseurByCommandeFournisseurId(
			String idCommande) {

		return ligneCommandeFournisseurRepository.findAllByCommandeFournisseurId(idCommande).stream()
				.map(ligneCommandeFournisseurMapper::toDto).collect(Collectors.toList());

	}

	@Override
	public CommandeFournisseurDto updateProduit(String idCommande, String idLigneCommande, String idProduit)
			throws NotFoundException {
		checkIdCommande(idCommande);
		checkIdLigneCommande(idLigneCommande);
		checkIdProduit(idProduit, "nouvel");

		CommandeFournisseurDto commandeFournisseur = checkEtatCommande(idCommande);

		Optional<LigneCommandeFournisseur> ligneCommandeFournisseur = findLigneCommandeFournisseur(idLigneCommande);

		Optional<Produit> produitOptional = produitRepository.findById(idProduit);
		if (produitOptional.isEmpty()) {
			throw new NotFoundException("Aucune article n'a ete trouve avec l'ID " + idProduit);
		}

		LigneCommandeFournisseur ligneCommandeFournisseurToSaved = ligneCommandeFournisseur.get();
		ligneCommandeFournisseurToSaved.setProduits(produitOptional.get());
		ligneCommandeFournisseurRepository.save(ligneCommandeFournisseurToSaved);

		return commandeFournisseur;
	}

	private void checkIdCommande(String idCommande) throws NotFoundException {
		if (!commandeFournisseurRepository.existsById(idCommande)) {
			throw new NotFoundException("Category not found ");
		}
	}

	private CommandeFournisseurDto checkEtatCommande(String idCommande) throws NotFoundException {
		CommandeFournisseurDto commandeFournisseur = findById(idCommande);
		if (commandeFournisseur.isCommandeLivree()) {
			throw new NotFoundException("Impossible de modifier la commande lorsqu'elle est livree");
		}
		return commandeFournisseur;
	}

	private void checkIdLigneCommande(String idLigneCommande) throws NotFoundException {
		if (idLigneCommande == null) {
			log.error("L'ID de la ligne commande is NULL");
			throw new NotFoundException("Impossible de modifier l'etat de la commande avec une ligne de commande null");
		}
	}

	private void checkIdProduit(String idProduit, String msg) throws NotFoundException {
		if (idProduit == null) {
			log.error("L'ID de " + msg + " is NULL");
			throw new NotFoundException(
					"Impossible de modifier l'etat de la commande avec un " + msg + " ID article null");
		}
	}

	private Optional<LigneCommandeFournisseur> findLigneCommandeFournisseur(String idLigneCommande)
			throws NotFoundException {
		Optional<LigneCommandeFournisseur> ligneCommandeFournisseurOptional = ligneCommandeFournisseurRepository
				.findById(idLigneCommande);
		if (ligneCommandeFournisseurOptional.isEmpty()) {
			throw new NotFoundException(
					"Aucune ligne commande fournisseur n'a ete trouve avec l'ID " + idLigneCommande);
		}
		return ligneCommandeFournisseurOptional;
	}

	@Override
	public CommandeFournisseurDto deleteProduit(String idCommande, String idLigneCommande) throws NotFoundException {
		     checkIdCommande(idCommande);
		     checkIdLigneCommande(idLigneCommande);

		    CommandeFournisseurDto commandeFournisseur = checkEtatCommande(idCommande);
		    // Il suffit de vérifier la LigneCommandeFournisseur et d'informer le fournisseur en cas d'absence
		    findLigneCommandeFournisseur(idLigneCommande);
		    ligneCommandeFournisseurRepository.deleteById(idLigneCommande);

		    return commandeFournisseur;
	}

	@Override
	public CommandeFournisseurDto updateQuantiteCommande(String idCommande, String idLigneCommande,
			BigDecimal quantite) throws NotFoundException{
		checkIdCommande(idCommande);
	    checkIdLigneCommande(idLigneCommande);

	    if (quantite == null || quantite.compareTo(BigDecimal.ZERO) == 0) {
	      log.error("L'ID de la ligne commande is NULL");
	      throw new NotFoundException("Impossible de modifier l'etat de la commande avec une quantite null ou ZERO");
	    }

	    CommandeFournisseurDto commandeFournisseur = checkEtatCommande(idCommande);
	    Optional<LigneCommandeFournisseur> ligneCommandeFournisseurOptional = findLigneCommandeFournisseur(idLigneCommande);

	    LigneCommandeFournisseur ligneCommandeFounisseur = ligneCommandeFournisseurOptional.get();
	    ligneCommandeFounisseur.setQuantite(quantite);
	    ligneCommandeFournisseurRepository.save(ligneCommandeFounisseur);

	    return commandeFournisseur;
	}
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	
	

}

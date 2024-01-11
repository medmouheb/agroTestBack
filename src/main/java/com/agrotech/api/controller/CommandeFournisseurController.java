package com.agrotech.api.controller;

import java.math.BigDecimal;
import java.util.List;

import com.agrotech.api.Repository.CommandeFournisseurRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.agrotech.api.dto.CommandeFournisseurDto;
import com.agrotech.api.dto.LigneCommandeFournisseurDto;
import com.agrotech.api.enums.EtatCommande;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.services.CommandeFournisseurService;

import lombok.RequiredArgsConstructor;

@CrossOrigin(origins = {"http://localhost:4200"}, maxAge = 3600)
@RestController
@RequestMapping("/commandeFournisseur")
@RequiredArgsConstructor
public class CommandeFournisseurController {

	@Autowired
	private CommandeFournisseurService commandeFournisseurService ;

	private  final CommandeFournisseurRepository commandeFournisseurRepository;
	@DeleteMapping("/deleteall")
	public void deleteall() throws NotFoundException {
		commandeFournisseurRepository.deleteAll();
	}
	@PostMapping("")
	public ResponseEntity<?> create(@RequestBody CommandeFournisseurDto commandeFournisseur) {
		CommandeFournisseurDto response = commandeFournisseurService.create(commandeFournisseur);
		return new ResponseEntity<>(response, HttpStatus.CREATED);
	}



	@PutMapping("/{id}")
	public ResponseEntity<?> update(@PathVariable String id, @RequestBody CommandeFournisseurDto commandeFournisseur) throws NotFoundException {
		CommandeFournisseurDto response = commandeFournisseurService.update(id, commandeFournisseur);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("/{id}")
	public ResponseEntity<?> findAll(@PathVariable String id) throws NotFoundException {
		CommandeFournisseurDto response = commandeFournisseurService.findById(id);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}

	@GetMapping("")
	public ResponseEntity<?> findAll() {
		List<CommandeFournisseurDto> response = commandeFournisseurService.findAll();
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	@GetMapping("/by-code/{code}")
	public ResponseEntity<?> findByCode(@PathVariable String code) throws NotFoundException {
		CommandeFournisseurDto response = commandeFournisseurService.findByCode(code);
		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	@DeleteMapping("/{id}")
	 public ResponseEntity<?> delete(@PathVariable String id) throws NotFoundException {
		commandeFournisseurService.delete(id);
		return new ResponseEntity<>(true, HttpStatus.OK);
	}


	@PatchMapping("/update/etat/{idCommande}/{etatCommande}")
	public  ResponseEntity<?> updateEtatCommande(@PathVariable("idCommande") String idCommande, @PathVariable("etatCommande") EtatCommande etatCommande) throws NotFoundException {
		CommandeFournisseurDto response = commandeFournisseurService.updateEtatCommande(idCommande, etatCommande);
	    return new ResponseEntity<>(response, HttpStatus.OK);
	  }

	@PatchMapping("/update/quantite/{idCommande}/{idLigneCommande}/{quantite}")
	public  ResponseEntity<?> updateQuantiteCommande(@PathVariable("idCommande") String idCommande,
		      @PathVariable("idLigneCommande") String idLigneCommande, @PathVariable("quantite") BigDecimal quantite) throws NotFoundException{
		CommandeFournisseurDto response =commandeFournisseurService.updateQuantiteCommande(idCommande, idLigneCommande, quantite);

		return new ResponseEntity<>(response, HttpStatus.OK);
	}


	 @PatchMapping( "/update/fournisseur/{idCommande}/{idFournisseur}")
	 public  ResponseEntity<?>  updateFournisseur(@PathVariable("idCommande") String idCommande, @PathVariable("idFournisseur") String idFournisseur) throws NotFoundException {
		 CommandeFournisseurDto response = commandeFournisseurService.updateFournisseur(idCommande, idFournisseur);
			return new ResponseEntity<>(response, HttpStatus.OK);

	 }


	 @PatchMapping("/update/article/{idCommande}/{idLigneCommande}/{idArticle}")
	 public  ResponseEntity<?> updateProduit(@PathVariable("idCommande") String idCommande,
	      @PathVariable("idLigneCommande") String idLigneCommande, @PathVariable("idArticle") String idArticle) throws NotFoundException {
		 CommandeFournisseurDto response = commandeFournisseurService.updateProduit(idCommande, idLigneCommande, idArticle);
			return new ResponseEntity<>(response, HttpStatus.OK);

	 }


	 @GetMapping("/lignesCommande/{idCommande}")
	 public  List<LigneCommandeFournisseurDto> findAllLignesCommandesFournisseurByCommandeFournisseurId(@PathVariable("idCommande") String idCommande) throws NotFoundException {

		 return commandeFournisseurService.findAllLignesCommandesFournisseurByCommandeFournisseurId(idCommande);

	 }


	 @DeleteMapping("/delete/produit/{idCommande}/{idLigneCommande}")
	 public ResponseEntity<?> deleteProduit(@PathVariable("idCommande") String idCommande, @PathVariable("idLigneCommande") String idLigneCommande) throws NotFoundException {
		 CommandeFournisseurDto response = commandeFournisseurService.deleteProduit(idCommande, idLigneCommande);
			return new ResponseEntity<>(response, HttpStatus.OK);

	 }





}

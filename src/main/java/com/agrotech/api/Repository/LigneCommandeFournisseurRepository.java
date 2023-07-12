package com.agrotech.api.Repository;

import java.util.List;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.agrotech.api.model.LigneCommandeFournisseur;

@Repository
public interface LigneCommandeFournisseurRepository extends MongoRepository<LigneCommandeFournisseur, String>{
	
	List<LigneCommandeFournisseur> findAllByCommandeFournisseurId(String idCommande);


}

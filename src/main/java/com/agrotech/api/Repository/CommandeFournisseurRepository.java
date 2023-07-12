package com.agrotech.api.Repository;

import java.util.Optional;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.agrotech.api.model.CommandeFournisseur;

@Repository
public interface CommandeFournisseurRepository  extends MongoRepository<CommandeFournisseur, String>{
	  Optional<CommandeFournisseur> findCommandeFournisseurByCode(String code);

   
}

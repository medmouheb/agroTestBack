package com.agrotech.api.model;

import java.math.BigDecimal;
import java.util.ArrayList;
import java.util.HashSet;

import lombok.*;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.agrotech.api.utils.ValidationMessages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "produit")
public class Produit extends BaseEntity {

	@NotBlank(message = ValidationMessages.CODE_REQUIRED)
	@Indexed(unique = true)
	@Size(max = 50, message = ValidationMessages.CODE_TOO_LONG)
	private String code;
	@NotBlank(message = ValidationMessages.NAME_REQUIRED)
	@Size(max = 250, message = ValidationMessages.NAME_TOO_LONG)
	private String name;
	@NotBlank(message = ValidationMessages.TYPE_REQUIRED)
	private String type;
	@Size(max = 250)
	private String num;
	@Size(max = 250)
	private Boolean statuss;
	@Size(max = 250)
	private String currency;
	@Size(max = 250)
	private String Inventaire;
	private Boolean Medicamenteux;
	@Size(max = 250)
	private String Fabricant;
	@Size(max = 250)
	private String couleur;
	@Size(max = 250)
	private String maxdepasse;
	@Size(max = 250)
	private BigDecimal prixUnitaireHt;
	@Size(max = 250)
	private BigDecimal tauxTva;

	@Size(max = 250)
	private BigDecimal prixUnitaireTtc;

	private Boolean isDeleted=false;


	private String methodeutilisation;


	private String transactionDate;
	private String farmCode;
	private String houseCode ;

	private String category;

	private Fournisseur fournisseur;

	private VendorSKU vendorSKU;
	private SalesSKU salesSKU;

	private HashSet<String> tags = new HashSet<>();


	private String farmer;


}

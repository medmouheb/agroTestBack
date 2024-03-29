package com.agrotech.api.dto;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import org.springframework.data.mongodb.core.index.Indexed;

import com.agrotech.api.model.*;
import com.agrotech.api.utils.ValidationMessages;

import java.math.BigDecimal;
import java.util.HashSet;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Data
public class ProduitDto extends BaseDto {


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

	private Fournisseur fournisseur;
	private String category;

	private String transactionDate;
	private String farmCode;
	private String houseCode ;
	private VendorSKU vendorSKU;
	private SalesSKU salesSKU;
	private String farmer;

	private BigDecimal  stockMinimum;
	private BigDecimal  stockMinimumAlert;

	private BigDecimal  ttc;
	private BigDecimal  lastSallPrice;
	private BigDecimal  actualStock= BigDecimal.valueOf(0);

	private String unit;

	private String picture;

	private String barCode;




	private HashSet<String> tags = new HashSet<>();




}

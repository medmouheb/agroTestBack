package com.agrotech.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;
import org.springframework.data.mongodb.core.mapping.Field;

import com.agrotech.api.utils.ValidationMessages;
import com.fasterxml.jackson.annotation.JsonProperty;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "fournisseur")
public class Fournisseur extends BaseEntity {

	// obligatoire
	@NotBlank(message = ValidationMessages.CODE_REQUIRED)
	@Indexed(unique = true)
	@Size(max = 50, message = ValidationMessages.CODE_TOO_LONG)
	private String code;
	@NotBlank(message = ValidationMessages.NAME_REQUIRED)
	@Size(max = 250, message = ValidationMessages.NAME_TOO_LONG)
	private String name;
//	@NotBlank(message = ValidationMessages.TYPE_REQUIRED)
	private String type;
//	@NotBlank(message = ValidationMessages.PAYMENT_TERM_REQUIRED)
	@Size(max = 20, message = ValidationMessages.PAYMENT_TERM_LONG)
	private String paymentTerm;
	//@NotBlank(message = ValidationMessages.CURRENCY_REQUIRED)
	@Size(max = 255, message = ValidationMessages.CURRENCY_LONG)
	private String currencycode;

	// optional
	@Size(max = 500)
	private String address;
	@Size(max = 10)
	private String codeCity;
	@Size(max = 100)
	private String nameCity;
	@Size(max = 100)
	private String wilayaName;
	@Size(max = 3)
	private String wilayaCode;
	@Size(max = 12)
	private String phone;
	@Size(max = 150)
	private String email;
	@Size(max = 10)
	private String zipCode;
	@DBRef
	private VendorSKU vendorSKU;
	private Boolean isDeleted=false;
	private String vendorSKUcode;
	private String vendorSKUname;
	private String currencyname;

}

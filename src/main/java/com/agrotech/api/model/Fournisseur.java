package com.agrotech.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.*;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.agrotech.api.utils.ValidationMessages;

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
	private String type;
	@Size(max = 20, message = ValidationMessages.PAYMENT_TERM_LONG)
	private String paymentTerm;

	private String currency;

	// optional
	@Size(max = 500)
	private String address;
	@Size(max = 10)
	private String codeCity;
	@Size(max = 100)
	private String nameCity;
	@Size(max = 100)
	private String wilayaName;
	@Size(max = 50)
	private String wilayaCode;
	@Size(max = 12)
	private String phone;
	@Size(max = 150)
	private String email;
	@Size(max = 10)
	private String zipCode;

	private String vendorSKU;
	private Boolean isDeleted=false;

	@Size(max = 12)
	private String vendorSKUcode;
	@Size(max = 150)
	private String vendorSKUname;
	@Size(max = 10)
	private String currencycode;

	@Size(max = 50)
	private String currencyname;

	private String farmer;

	@Size(max = 12)
	private String shippingCity;
	@Size(max = 150)
	private String shippingAddress;

}

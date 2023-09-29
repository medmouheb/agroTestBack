package com.agrotech.api.dto;

import org.springframework.data.mongodb.core.index.Indexed;


import com.agrotech.api.utils.ValidationMessages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FournisseurDto extends BaseDto {
	// obligatoire
	@NotBlank(message = ValidationMessages.CODE_REQUIRED)
	@Indexed(unique = true)
	@Size(max = 50, message = ValidationMessages.CODE_TOO_LONG)
	private String code;
	@NotBlank(message = ValidationMessages.NAME_REQUIRED)
	@Size(max = 250, message = ValidationMessages.NAME_TOO_LONG)
	private String name;
	//@NotBlank(message = ValidationMessages.TYPE_REQUIRED)
	private String type;
	//	@NotBlank(message = ValidationMessages.PAYMENT_TERM_REQUIRED)
	@Size(max = 20, message = ValidationMessages.PAYMENT_TERM_LONG)
	private String paymentTerm;
	//		@NotBlank(message = ValidationMessages.CURRENCY_REQUIRED)
	@Size(max = 3, message = ValidationMessages.CURRENCY_LONG)
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
	@Size(max = 12)
	private String vendorSKUcode;
	@Size(max = 150)
	private String vendorSKUname;
	@Size(max = 10)
	private String currencycode;

	@Size(max = 50)
	private String currencyname;


	@Size(max = 12)
	private String shippingCity;
	@Size(max = 150)
	private String shippingAddress;


	private String vendorSKU;

	private Boolean isDeleted = false;


}
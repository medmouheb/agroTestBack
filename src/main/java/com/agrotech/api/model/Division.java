package com.agrotech.api.model;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

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
@Document(collection = "division")
public class Division extends BaseEntity {

	@NotBlank(message = ValidationMessages.DIVISION_CODE_REQUIRED)
	@Indexed(unique = true)
	@Size(max = 10, message = ValidationMessages.DIVISION_CODE_LONG)
	private String code ;
	@NotBlank(message = ValidationMessages.DIVISION_NAME_REQUIRED)
	@Size(max = 250, message = ValidationMessages.DIVISION_NAME_LONG)
	private String name ;
	@NotBlank(message = ValidationMessages.SPECIES_TYPE_REQUIRED)
	@Size(max = 20)
	private String speciesType ;
	private String measurement ;
	@Size(max = 500)
	private String address;
	@Size(max = 10)
	private String codeCity;
	@Size(max = 100)
	private String nameCity;
	@Size(max = 10)
	private String Currencycode;
	@Size(max = 100)
	private String Currencyname;
	@Size(max = 10)
	private String Companycode;
	@Size(max = 100)
	private String Companyname;
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
	private String divisiontype;
	private Boolean isDeleted=false;





}

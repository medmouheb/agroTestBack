package com.agrotech.api.model;

import org.springframework.data.mongodb.core.index.Indexed;

import com.agrotech.api.utils.ValidationMessages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Document;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "salesSku")
public class SalesSKU extends BaseEntity{

	@NotBlank(message = ValidationMessages.CODE_REQUIRED)
	@Indexed(unique = true)
	@Size(max = 50, message = ValidationMessages.CODE_TOO_LONG)
	private String code;
	@Indexed(unique = true)
	@Size(max = 250, message = ValidationMessages.NAME_TOO_LONG)
	private String name ;
	@NotBlank(message = ValidationMessages.CODE_REQUIRED)
	@Indexed(unique = true)
	@Size(max = 50, message = ValidationMessages.CODE_TOO_LONG)
	private String sailorCode;
	@NotBlank(message = ValidationMessages.NAME_REQUIRED)
	@Size(max = 250, message = ValidationMessages.NAME_TOO_LONG)
	private String sailorNameSku;
	@NotBlank(message = ValidationMessages.unitDescription_REQUIRED)
	@Size(max = 50, message = ValidationMessages.unitDescription_TOO_LONG)
	private String sailorCodeSku;
	private Boolean isDeleted=false;
}

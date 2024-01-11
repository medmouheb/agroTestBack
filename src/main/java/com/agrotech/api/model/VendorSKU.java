package com.agrotech.api.model;

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
public class VendorSKU extends BaseEntity {

	@NotBlank(message = ValidationMessages.CODE_REQUIRED)
	@Indexed(unique = true)
	private String code ;
	//@NotBlank(message = ValidationMessages.NAME_REQUIRED)
	@Indexed(unique = true)
	@Size(max = 10, message = ValidationMessages.NAME_TOO_LONG)
	private String name ;
	@NotBlank(message = ValidationMessages.CODE_REQUIRED)
	@Indexed(unique = true)
	@Size(max = 50, message = ValidationMessages.CODE_TOO_LONG)
	private String vendorSKUCode;
	@NotBlank(message = ValidationMessages.NAME_REQUIRED)
	@Size(max = 250, message = ValidationMessages.NAME_TOO_LONG)
	private String vendorSKUName;
	private String vendorCode;
	private Boolean isDeleted=false;

}

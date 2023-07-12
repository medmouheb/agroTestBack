package com.agrotech.api.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.agrotech.api.model.Produit;
import com.agrotech.api.model.VendorSKU;
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
public class CategoryDto extends BaseDto {
	
	

	 @NotBlank(message = ValidationMessages.CODE_REQUIRED)
	 @Indexed(unique = true)
	 private String code;
	@NotBlank(message = ValidationMessages.CODE_REQUIRED)
	@Indexed(unique = true)
	private String name;
	 @NotBlank(message = ValidationMessages.DESCRIPTION_REQUIRED)
	 @Size(max = 250, message = ValidationMessages.DESCRIPTION_TOO_LONG)
	 private String description;
	private Boolean isDeleted=false;
	 @DBRef
	 private Set<Produit> produit = new HashSet<>();

}

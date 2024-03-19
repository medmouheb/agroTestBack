package com.agrotech.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.agrotech.api.utils.ValidationMessages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "category")
public class Category extends BaseEntity {


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



	private String farmer;





}

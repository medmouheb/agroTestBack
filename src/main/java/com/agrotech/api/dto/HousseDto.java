package com.agrotech.api.dto;

import org.springframework.data.mongodb.core.index.Indexed;

import com.agrotech.api.enums.Etat;
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
public class HousseDto extends BaseDto{
	@NotBlank(message = ValidationMessages.CODE_REQUIRED)
	@Indexed(unique = true)
	@Size(max = 50, message = ValidationMessages.CODE_TOO_LONG)
	private String code ; 
	@NotBlank(message = ValidationMessages.NAME_REQUIRED)
	@Size(max = 250, message = ValidationMessages.NAME_TOO_LONG)
	private String name ;
	private Etat etat ;
	private String length ;
	private String width  ;
	private String area ;
	private String year ;
	private String dateBegin ;
	private String dateEnd ;
	private String capacity ;
	private String farmer;

}

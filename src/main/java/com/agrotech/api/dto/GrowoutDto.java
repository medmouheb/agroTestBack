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
public class GrowoutDto extends BaseDto{
	
	@NotBlank(message = ValidationMessages.CODE_REQUIRED)
	@Indexed(unique = true)
	private String code ; 
    @NotBlank(message = ValidationMessages.NAME_REQUIRED)
    @Size(max = 250, message = ValidationMessages.NAME_TOO_LONG)
	private String name ; 
    @Size(max = 500)
	private String address ;
    @Size(max = 10)
	private String codeCity ;
    @Size(max = 100)
	private String nameCity ;
    @Size(max = 3)
	private String wilayaCode ;
    @Size(max = 100)
	private String wilayaName ;
    @Size(max = 10)
	private String zipCode ;
    @Size(max = 12)
	private String phoneNumber ;
    @Size(max = 150)
	private String email ;
	@Size(max = 10)
	private String divisionCode ;
	@Size(max = 250)
	private String divisionName ;
	private Boolean isDeleted=false;

}

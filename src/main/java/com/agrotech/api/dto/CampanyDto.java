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
public class CampanyDto extends BaseDto{
	
	
	@NotBlank(message = ValidationMessages.CODE_REQUIRED)
    @Indexed(unique = true)
	private String code ; 
    @NotBlank(message = ValidationMessages.NAME_REQUIRED)
    @Size(max = 250, message = ValidationMessages.NAME_TOO_LONG)
    @Indexed(unique = true)
	private String name="" ;
    //optional
    @Size(max = 500)
	private String address ;
    @Size(max = 10)
	private String cityCode ;
    @Size(max = 100)
	private String cityName ;
    @Size(max = 3)
	private String wilayaName ;
    @Size(max = 3)
    private String wilayaCode ;
    @Size(max = 10)
	private String zipCode ;
    @Size(max = 12)
	private String number ;
    @Size(max = 150)
	private String email ;
    private Boolean isDeleted=false;

}

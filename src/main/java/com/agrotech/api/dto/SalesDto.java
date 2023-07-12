package com.agrotech.api.dto;

import java.util.HashSet;
import java.util.Set;

import org.springframework.data.mongodb.core.mapping.DBRef;

import com.agrotech.api.enums.statusVente;
import com.agrotech.api.model.SalesSKU;

import jakarta.validation.constraints.NotBlank;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class SalesDto extends BaseDto {
	
	@NotBlank(message = "Code is required")
	private String code ;
	@NotBlank(message = "Name is required")
    private String name;
    private String type ;
    private String Udescription ;
    private String Uinventaire  ;
    private statusVente statusVente ;
    private String Transaction_Basis ;
    private String Payment_Type ;
    private String payee_type ;
    private Boolean isDeleted=false;
    @DBRef
    private Set<SalesSKU> salesSku = new HashSet<>();
	

}

package com.agrotech.api.model;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.agrotech.api.enums.Etat;
import com.agrotech.api.utils.ValidationMessages;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "ferme")
public class Ferme extends BaseEntity {

	@NotBlank(message = ValidationMessages.CODE_REQUIRED)
	@Indexed(unique = true)
	private String code;
	@NotBlank(message = ValidationMessages.NAME_REQUIRED)
	@Size(max = 250, message = ValidationMessages.NAME_TOO_LONG)
	private String nom;
	private Etat statuss;
	private String stage;
	private String grouwout;
	@NotBlank(message = ValidationMessages.TYPE_REQUIRED)
	@Size(max = 250, message = ValidationMessages.TYPE_TOO_LONG)
	private String type;
	@Size(max = 250)
	private String ferme_Type;
	@Size(max = 250)
	private String status_Construction;
	@Size(max = 250)
	private String num_Client;
	@NotBlank(message = ValidationMessages.CODE_MANAGER_REQUIRED)
	@Size(max = 250)
	private String manager_Code;
	@NotBlank(message = ValidationMessages.NAME_MANGER_REQUIRED)
	@Size(max = 250)
	private String manager_name;
	@NotBlank(message = ValidationMessages.CODE_TECHNICIEN_REQUIRED)
	@Size(max = 250)
	private String technician_Code;
	@NotBlank(message = ValidationMessages.NAME_TECHNICIEN_REQUIRED)
	@Size(max = 250)
	private String technician_Name;
	@Size(max = 250)
	private String area_type;
	@NotBlank(message = "Owner name is required")
	@NotBlank(message = ValidationMessages.NAME_AWNER_REQUIRED)
	private String owner_Name;
	@Size(max = 250)
	private String attachments;
	@Size(max = 250)
	private String address1;
	@Size(max = 250)
	private String address2;
	@Size(max = 10)
	private String city_Code;
	@Size(max = 100)
	private String city_Name;
	@Size(max = 10)
	private String governorateCode;
	@Size(max = 100)
	private String governorateName;
	@Size(max = 12)
	private String zip_Code;
	@Size(max = 500)
	private String email;
	@Size(max = 12)
	private String telephone;
	@Size(max = 12)
	private String phoneNumber;
	@Size(max = 12)
	private String faxNumber;
	@Size(max = 250)
	private String farm_Area;
	@Size(max = 250)
	private String latitude;
	@Size(max = 250)
	private String longitude;
	private Boolean isDeleted=false;

	@DBRef
	private String cost_Center ;
	@DBRef
	private Warehouse warehouse;
	@DBRef
	private Fournisseur vendor;

	// private String customerCode ;
	// private String customerName ;

}

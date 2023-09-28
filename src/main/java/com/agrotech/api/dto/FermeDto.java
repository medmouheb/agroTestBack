package com.agrotech.api.dto;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Set;

import com.agrotech.api.model.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.agrotech.api.enums.Etat;
import com.agrotech.api.utils.ValidationMessages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.Field;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class FermeDto extends BaseDto {
	@NotBlank(message = ValidationMessages.CODE_REQUIRED)
	@Indexed(unique = true)
	private String code;
	@NotBlank(message = ValidationMessages.NAME_REQUIRED)
	@Size(max = 250, message = ValidationMessages.NAME_TOO_LONG)
	private String nom;
	private Boolean statuss;
	private String stage;
	private String costcenter;
	private String warehousecode;
	private String vendorcode;
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
	private String comments;
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
	//
	private String growoutcode;
	@DBRef
	private CostCenter cost_Center ;
	@DBRef
	private Warehouse warehouse;
	@DBRef
	private Fournisseur vendor;
	@DBRef
	private Growout growout;
	private String product;
	private String land;
	private String planning;
	private String logistic;
	private String feedMillcode;
	private String primaryMarket;
	private String liveProductday;
	private String maxturckcapacity;
	private String payee;


	//projection
	private String projectwarehouse;
	private String projectligistic;

	//distance
	private String distnfarmlogi;
	private String distnfarmMarket;


	//Visitors Logs
	private String VisitDate;
	private String FirstNameVisit;
	private String LastNameVisit;
	private String IDNumberVisit;
	private String TimeinVisit;
	private String TimeoutVisit;
	private String Purposeofthevisit;

	//cezrtiff
	private String Certifications;

	//Contract Farms
	private String ContractNumber;
	private String contratPay;
	private LocalDate startDateFarms;
	private LocalDate endDateFarms;
	private LocalDate renewalDateFarms;

	//Paiement information
	private String PaymentType;//Checking, saving or paycheck 
	private String	AccountNumber;
	private String	BankName;
	private String	BankCode;
	private String	Bankaddress;
	private String	DirectDepositflag;
	private String	typepayment ;//auto or not
	private LocalDate OperationDate; //satesystem
	private String	amounttransferred;
	//Resource Information
	private String ressourceInformation;
	private String statutInformation;
	private String adressInformation;
	private String FirstNameInformation;
	private String LastNameInformation;
	private String phoneInformation;
	private LocalDate startDateInformation;
	private LocalDate endDateInformation;

	private String customerCode ;
	private String customerName ;

	private String  warehouseprimary ;
	private String  warehousesecondry ;
	@Field("properties")

	private List<PropertyDTO> properties;


}

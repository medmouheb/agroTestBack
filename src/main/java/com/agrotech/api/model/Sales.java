package com.agrotech.api.model;


import lombok.*;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.Document;

import com.agrotech.api.utils.ValidationMessages;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

import java.util.ArrayList;
import java.util.List;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "sales")
public class Sales  extends BaseEntity{

	@NotBlank(message = ValidationMessages.CODE_REQUIRED)
	@Indexed(unique = true)
	@Size(max = 50, message = ValidationMessages.CODE_TOO_LONG)
	private String code ;
	@NotBlank(message = ValidationMessages.NAME_REQUIRED)
	@Size(max = 250, message = ValidationMessages.NAME_TOO_LONG)
    private String name;
    private String type ;
    private String currency ;
    private String Payment_Term ;
    private Boolean isDeleted=false;

	private  String buyer;

	private List<Tax> taxes = new ArrayList<>();



	private String purchaseOrder;
	private String quotation;
	private String salesInvoice;
	private String deliveryNote;
	private String creditNote;

	private String product;


	@Override
	public String toString() {
		return "Sales{" +
				"code='" + code + '\'' +
				", name='" + name + '\'' +
				", type='" + type + '\'' +
				", currency='" + currency + '\'' +
				", Payment_Term='" + Payment_Term + '\'' +
				", isDeleted=" + isDeleted +
				", taxes=" + taxes +
				'}';
	}
}

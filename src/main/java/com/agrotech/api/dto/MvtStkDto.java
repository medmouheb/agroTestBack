package com.agrotech.api.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.Date;
import java.util.HashSet;
import java.util.Set;

import com.agrotech.api.model.Stock;
import com.agrotech.api.utils.ValidationMessages;
import jakarta.validation.constraints.NotBlank;
import org.springframework.data.mongodb.core.index.Indexed;
import org.springframework.data.mongodb.core.mapping.DBRef;

import com.agrotech.api.enums.SourceMvtStk;
import com.agrotech.api.enums.TypeMvtStk;
import com.agrotech.api.model.Produit;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
public class MvtStkDto extends BaseDto{
	@NotBlank(message = ValidationMessages.CODE_REQUIRED)
	@Indexed(unique = true)
	private String code ;
	private Date dateMvt;
	private BigDecimal quantite;
	private String typeMvt;
	private Stock stock;

	private String farmer;

	private Boolean isDeleted=false;

}

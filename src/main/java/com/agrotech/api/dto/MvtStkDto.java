package com.agrotech.api.dto;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

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

	
	private String id ;
	private Instant dateMvt;
    private BigDecimal quantite;
	private TypeMvtStk typeMvt;
	private SourceMvtStk sourceMvt;
 

    @DBRef
    private Set<Produit> produits = new HashSet<>();
	
	
	
}

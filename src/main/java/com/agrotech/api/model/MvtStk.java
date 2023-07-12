package com.agrotech.api.model;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import org.springframework.data.mongodb.core.mapping.DBRef;
import org.springframework.data.mongodb.core.mapping.Document;

import com.agrotech.api.enums.SourceMvtStk;
import com.agrotech.api.enums.TypeMvtStk;

import java.math.BigDecimal;
import java.time.Instant;
import java.util.HashSet;
import java.util.Set;

@Getter
@Setter
@AllArgsConstructor
@NoArgsConstructor
@Document(collection = "mvt_stk")
public class MvtStk {

	private String id ;
	private Instant dateMvt;
    private BigDecimal quantite;
	private TypeMvtStk typeMvt;
	private SourceMvtStk sourceMvt;
 

    @DBRef
    private Set<Produit> produits = new HashSet<>();
}

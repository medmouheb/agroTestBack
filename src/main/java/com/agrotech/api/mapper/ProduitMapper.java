package com.agrotech.api.mapper;

import org.mapstruct.Mapper;
import org.mapstruct.Mapping;
import org.mapstruct.Named;

import com.agrotech.api.dto.ProduitDto;
import com.agrotech.api.model.Produit;
import org.springframework.stereotype.Component;

import java.math.BigDecimal;

@Mapper(uses = {
        FournisseurMapper.class
})
@Component

public interface ProduitMapper extends BaseMapper<ProduitDto, Produit> {
  
    @Mapping(target = "prixUnitaireHt", source = ".", qualifiedByName = "toPrixTTC")
    @Override
    ProduitDto toDto(Produit entity);


    @Named("toPrixTTC")
    default BigDecimal calculateToPrixTTC(Produit produit) {
        if (
                produit.getPrixUnitaireHt() == null || produit.getPrixUnitaireHt().intValue() == 0 ||
                        produit.getTauxTva() == null || produit.getTauxTva().intValue() == 0
        ) {
            return new BigDecimal(0);
        }
        return produit.getPrixUnitaireHt().add(produit.getPrixUnitaireHt().multiply(produit.getTauxTva().divide(new BigDecimal(100))));
    }
    
}

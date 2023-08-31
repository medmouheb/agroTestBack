package com.agrotech.api.mapper;

import com.agrotech.api.dto.UtilisationDuProduitDto;
import com.agrotech.api.model.UtilisationDuProduit;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface UtilisationDuProduitMapper extends BaseMapper<UtilisationDuProduitDto, UtilisationDuProduit>{
}

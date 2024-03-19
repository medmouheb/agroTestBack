package com.agrotech.api.mapper;

import com.agrotech.api.dto.FournisseurDto;
import org.mapstruct.Mapper;


import com.agrotech.api.model.Fournisseur;
import org.springframework.stereotype.Component;

@Mapper
public interface FournisseurMapper extends BaseMapper<FournisseurDto, Fournisseur> {
}

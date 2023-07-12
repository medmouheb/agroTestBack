package com.agrotech.api.mapper;

import org.mapstruct.Mapper;

import com.agrotech.api.dto.FournisseurDto;
import com.agrotech.api.model.Fournisseur;
import org.springframework.stereotype.Component;

@Mapper()
@Component

public interface FournisseurMapper extends BaseMapper<FournisseurDto, Fournisseur> {
}

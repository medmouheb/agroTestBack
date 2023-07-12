package com.agrotech.api.mapper;

import org.mapstruct.Mapper;

import com.agrotech.api.dto.LigneCommandeFournisseurDto;
import com.agrotech.api.model.LigneCommandeFournisseur;
import org.springframework.stereotype.Component;

@Mapper()
@Component

public interface LigneCommandeFournisseurMapper extends BaseMapper<LigneCommandeFournisseurDto, LigneCommandeFournisseur> {

}

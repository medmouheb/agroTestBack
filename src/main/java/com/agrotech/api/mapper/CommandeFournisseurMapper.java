package com.agrotech.api.mapper;

import org.mapstruct.Mapper;

import com.agrotech.api.dto.CommandeFournisseurDto;
import com.agrotech.api.model.CommandeFournisseur;
import org.springframework.stereotype.Component;

@Mapper()
@Component

public interface CommandeFournisseurMapper extends BaseMapper<CommandeFournisseurDto, CommandeFournisseur> {

}

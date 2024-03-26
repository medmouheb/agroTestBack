package com.agrotech.api.mapper;

import org.mapstruct.Mapper;

import com.agrotech.api.dto.CommandeDto;
import com.agrotech.api.model.Commande;
import org.springframework.stereotype.Component;

@Mapper
public interface CommandeMapper extends BaseMapper<CommandeDto, Commande> {

}

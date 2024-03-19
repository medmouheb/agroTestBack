package com.agrotech.api.mapper;

import com.agrotech.api.dto.VehiculeDto;
import com.agrotech.api.model.Vehicule;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface VehiculeMapper extends BaseMapper<VehiculeDto, Vehicule>{
}

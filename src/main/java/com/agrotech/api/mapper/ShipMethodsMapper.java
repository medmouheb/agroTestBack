package com.agrotech.api.mapper;

import com.agrotech.api.dto.ShipMethodsDto;
import com.agrotech.api.model.ShipMethods;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface ShipMethodsMapper extends  BaseMapper <ShipMethodsDto, ShipMethods> {
}

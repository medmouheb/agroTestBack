package com.agrotech.api.mapper;

import com.agrotech.api.dto.VehicleTypeDto;
import com.agrotech.api.model.VehicleType;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface VehicleTypeMapper extends  BaseMapper<VehicleTypeDto,VehicleType > {
}

package com.agrotech.api.mapper;

import com.agrotech.api.dto.VehiclesDto;
import com.agrotech.api.model.Vehicles;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface VehiclesMapper extends BaseMapper<VehiclesDto, Vehicles>{
}

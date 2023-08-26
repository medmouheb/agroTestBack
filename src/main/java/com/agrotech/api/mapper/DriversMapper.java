package com.agrotech.api.mapper;

import com.agrotech.api.dto.DriversDto;
import com.agrotech.api.model.Drivers;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface DriversMapper extends BaseMapper<DriversDto, Drivers>{
}

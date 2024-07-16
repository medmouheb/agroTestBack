package com.agrotech.api.mapper;

import com.agrotech.api.dto.LogisticUnitDto;
import com.agrotech.api.model.LogisticUnit;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface LogisticUnitMapper extends BaseMapper<LogisticUnitDto, LogisticUnit>{
}

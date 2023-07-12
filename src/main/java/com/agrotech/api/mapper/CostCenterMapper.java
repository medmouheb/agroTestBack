package com.agrotech.api.mapper;

import org.mapstruct.Mapper;

import com.agrotech.api.dto.CostCenterDto;
import com.agrotech.api.model.CostCenter;
import org.springframework.stereotype.Component;

@Mapper()
@Component

public interface CostCenterMapper extends BaseMapper<CostCenterDto, CostCenter> {

	
}

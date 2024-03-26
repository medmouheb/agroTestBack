package com.agrotech.api.mapper;

import com.agrotech.api.dto.ProductionDto;
import com.agrotech.api.model.Production;
import org.mapstruct.Mapper;


@Mapper
public interface ProductionMapper extends BaseMapper<ProductionDto, Production>{
}

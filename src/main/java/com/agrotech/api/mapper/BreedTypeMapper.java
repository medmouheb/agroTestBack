package com.agrotech.api.mapper;

import com.agrotech.api.dto.BreedTypeDto;
import com.agrotech.api.model.BreedType;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface BreedTypeMapper extends BaseMapper<BreedTypeDto, BreedType> {

}

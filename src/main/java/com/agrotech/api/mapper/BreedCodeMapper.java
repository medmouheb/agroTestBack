package com.agrotech.api.mapper;

import com.agrotech.api.dto.BreedCodeDto;
import com.agrotech.api.model.BreedCode;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface BreedCodeMapper extends BaseMapper<BreedCodeDto, BreedCode>{
}

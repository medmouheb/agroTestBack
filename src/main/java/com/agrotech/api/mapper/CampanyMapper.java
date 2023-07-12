package com.agrotech.api.mapper;

import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.model.Campany;

@Mapper()
@Component
public interface CampanyMapper extends BaseMapper<CampanyDto, Campany > {

}

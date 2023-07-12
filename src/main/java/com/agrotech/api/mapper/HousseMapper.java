package com.agrotech.api.mapper;

import org.mapstruct.Mapper;

import com.agrotech.api.dto.HousseDto;
import com.agrotech.api.model.Housse;
import org.springframework.stereotype.Component;

@Mapper()
@Component

public interface HousseMapper extends BaseMapper<HousseDto, Housse> {

}

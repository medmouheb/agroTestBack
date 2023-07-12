package com.agrotech.api.mapper;

import org.mapstruct.Mapper;

import com.agrotech.api.dto.GrowoutDto;
import com.agrotech.api.model.Growout;
import org.springframework.stereotype.Component;

@Mapper()
@Component

public interface GrowoutMapper extends BaseMapper<GrowoutDto, Growout> {

}

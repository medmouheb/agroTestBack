package com.agrotech.api.mapper;

import com.agrotech.api.dto.WillayaDto;
import com.agrotech.api.model.Willaya;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface WillayaMapper extends BaseMapper<WillayaDto, Willaya>{
}

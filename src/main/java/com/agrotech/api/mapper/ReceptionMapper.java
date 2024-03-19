package com.agrotech.api.mapper;

import com.agrotech.api.dto.ReceptionDto;
import com.agrotech.api.model.Reception;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface ReceptionMapper extends  BaseMapper<ReceptionDto, Reception>{
}

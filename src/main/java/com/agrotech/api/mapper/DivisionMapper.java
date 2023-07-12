package com.agrotech.api.mapper;

import org.mapstruct.Mapper;

import com.agrotech.api.dto.DivisionDTO;
import com.agrotech.api.model.Division;
import org.springframework.stereotype.Component;

@Mapper
@Component

public interface DivisionMapper extends BaseMapper<DivisionDTO, Division> {

}

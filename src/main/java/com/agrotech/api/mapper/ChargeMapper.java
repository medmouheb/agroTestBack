package com.agrotech.api.mapper;


import org.mapstruct.Mapper;

import com.agrotech.api.dto.ChargeDto;
import com.agrotech.api.model.Charge;
import org.springframework.stereotype.Component;


@Mapper()
@Component
public interface ChargeMapper extends BaseMapper<ChargeDto, Charge> {
}

package com.agrotech.api.mapper;

import com.agrotech.api.dto.BrokersDto;
import com.agrotech.api.model.Brokers;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface BrokersMapper extends BaseMapper<BrokersDto, Brokers>{
}

package com.agrotech.api.mapper;

import com.agrotech.api.dto.BuyersDto;
import com.agrotech.api.model.Buyers;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface BuyersMapper extends BaseMapper<BuyersDto, Buyers>{
}

package com.agrotech.api.mapper;

import com.agrotech.api.dto.BuyersDto;
import com.agrotech.api.model.Buyers;
import com.agrotech.api.model.PotentialClient;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper

public interface BuyersMapper extends BaseMapper<BuyersDto, Buyers>{
    Buyers toEntity(PotentialClient potentialClient);
}

package com.agrotech.api.mapper;


import com.agrotech.api.dto.BuyersDto;
import com.agrotech.api.dto.PotentialClientDto;
import com.agrotech.api.model.Buyers;
import com.agrotech.api.model.PotentialClient;
import org.mapstruct.Mapper;

@Mapper
public interface PotentialClientMapper  extends BaseMapper<PotentialClientDto, PotentialClient>{
}

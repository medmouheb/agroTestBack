package com.agrotech.api.mapper;


import com.agrotech.api.dto.BinDetailsDto;
import com.agrotech.api.model.BinDetails;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface BinDetailsMapper extends BaseMapper<BinDetailsDto , BinDetails>  {
}

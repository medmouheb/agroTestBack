package com.agrotech.api.mapper;

import com.agrotech.api.dto.RapprochementDesStocksDto;
import com.agrotech.api.model.RapprochementDesStocks;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface RapprochementDesStocksMapper extends BaseMapper<RapprochementDesStocksDto, RapprochementDesStocks>{
}

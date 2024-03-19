package com.agrotech.api.mapper;


import com.agrotech.api.dto.StockDTO;
import com.agrotech.api.model.Stock;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface StockMapper  extends BaseMapper<StockDTO, Stock> {
}

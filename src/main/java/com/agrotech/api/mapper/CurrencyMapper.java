package com.agrotech.api.mapper;

import org.mapstruct.Mapper;

import com.agrotech.api.dto.CurrencyDto;
import com.agrotech.api.model.Currency;
import org.springframework.stereotype.Component;

@Mapper
public interface CurrencyMapper extends BaseMapper<CurrencyDto, Currency>{

}

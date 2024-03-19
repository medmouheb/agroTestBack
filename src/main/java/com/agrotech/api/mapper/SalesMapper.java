package com.agrotech.api.mapper;

import org.mapstruct.Mapper;

import com.agrotech.api.dto.SalesDto;
import com.agrotech.api.model.Sales;
import org.springframework.stereotype.Component;

@Mapper

public interface SalesMapper extends BaseMapper<SalesDto, Sales> {

}

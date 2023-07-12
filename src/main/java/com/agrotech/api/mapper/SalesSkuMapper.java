package com.agrotech.api.mapper;

import com.agrotech.api.dto.SalesSkuDto;
import com.agrotech.api.model.SalesSKU;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper()
@Component
public interface SalesSkuMapper extends BaseMapper<SalesSkuDto, SalesSKU>{
}

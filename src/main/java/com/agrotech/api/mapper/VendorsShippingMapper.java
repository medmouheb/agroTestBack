package com.agrotech.api.mapper;

import com.agrotech.api.dto.VendorsShippingDto;
import com.agrotech.api.model.VendorsShipping;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface VendorsShippingMapper extends BaseMapper<VendorsShippingDto, VendorsShipping>{
}

package com.agrotech.api.mapper;

import com.agrotech.api.dto.VendorTypeFreightDto;
import com.agrotech.api.model.VendorTypeFreight;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface VendorTypeFreightMapper extends BaseMapper<VendorTypeFreightDto, VendorTypeFreight>{
}

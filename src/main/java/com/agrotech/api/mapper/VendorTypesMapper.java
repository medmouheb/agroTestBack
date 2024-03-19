package com.agrotech.api.mapper;

import com.agrotech.api.dto.VendorTypesDto;
import com.agrotech.api.model.VendorTypes;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface VendorTypesMapper extends BaseMapper<VendorTypesDto, VendorTypes>{
}

package com.agrotech.api.mapper;

import com.agrotech.api.dto.VendorsInternalDetailsDto;
import com.agrotech.api.model.VendorsInternalDetails;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface VendorsInternalDetailMapper extends BaseMapper<VendorsInternalDetailsDto, VendorsInternalDetails>{
}

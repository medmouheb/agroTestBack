package com.agrotech.api.mapper;

import com.agrotech.api.dto.VendorTypeDetailsDto;
import com.agrotech.api.model.VendorTypeDetails;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface VendorTypeDetailsMapper extends BaseMapper<VendorTypeDetailsDto, VendorTypeDetails>{
}

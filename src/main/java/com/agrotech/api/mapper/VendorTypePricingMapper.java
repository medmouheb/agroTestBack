package com.agrotech.api.mapper;

import com.agrotech.api.dto.VendorTypePricingDto;
import com.agrotech.api.model.VendorTypePricing;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface VendorTypePricingMapper extends BaseMapper<VendorTypePricingDto, VendorTypePricing>{

}

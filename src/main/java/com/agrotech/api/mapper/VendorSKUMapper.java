package com.agrotech.api.mapper;

import com.agrotech.api.dto.VendorSKUDto;
import com.agrotech.api.model.VendorSKU;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper()
@Component
public interface VendorSKUMapper extends BaseMapper<VendorSKUDto, VendorSKU>{
}

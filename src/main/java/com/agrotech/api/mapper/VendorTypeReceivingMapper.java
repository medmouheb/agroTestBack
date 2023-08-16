package com.agrotech.api.mapper;

import com.agrotech.api.dto.VendorTypeReceivingDto;
import com.agrotech.api.model.VendorTypeReceiving;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface VendorTypeReceivingMapper extends BaseMapper<VendorTypeReceivingDto,VendorTypeReceiving>{
}

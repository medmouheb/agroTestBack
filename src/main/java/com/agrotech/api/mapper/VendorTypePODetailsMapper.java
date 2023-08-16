package com.agrotech.api.mapper;

import com.agrotech.api.dto.VendorTypePODetailsDto;
import com.agrotech.api.model.VendorTypePODetails;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface VendorTypePODetailsMapper extends BaseMapper<VendorTypePODetailsDto, VendorTypePODetails>{
}

package com.agrotech.api.mapper;

import com.agrotech.api.dto.VendorTypePurchaserDto;
import com.agrotech.api.model.VendorTypePurchaser;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper
public interface VendorTypePurchaserMapper extends BaseMapper<VendorTypePurchaserDto, VendorTypePurchaser>{
}

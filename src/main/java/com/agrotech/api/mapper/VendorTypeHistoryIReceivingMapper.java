package com.agrotech.api.mapper;

import com.agrotech.api.dto.VendorTypeHistoryIReceivingDto;
import com.agrotech.api.model.VendorTypeHistoryIReceiving;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface VendorTypeHistoryIReceivingMapper extends BaseMapper<VendorTypeHistoryIReceivingDto, VendorTypeHistoryIReceiving>{
}

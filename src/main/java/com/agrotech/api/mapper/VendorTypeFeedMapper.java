package com.agrotech.api.mapper;

import com.agrotech.api.dto.VendorTypeFeedDto;
import com.agrotech.api.model.VendorTypeFeed;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface VendorTypeFeedMapper extends BaseMapper<VendorTypeFeedDto,VendorTypeFeed>{
}

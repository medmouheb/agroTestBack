package com.agrotech.api.mapper;

import com.agrotech.api.dto.VendorsDto;
import com.agrotech.api.model.Vendors;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface VendorsMapper extends BaseMapper<VendorsDto, Vendors>{
}

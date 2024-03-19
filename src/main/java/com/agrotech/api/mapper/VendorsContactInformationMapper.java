package com.agrotech.api.mapper;

import com.agrotech.api.dto.VendorsContactInformationDto;
import com.agrotech.api.model.VendorsContactInformation;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface VendorsContactInformationMapper extends BaseMapper<VendorsContactInformationDto, VendorsContactInformation>{
}

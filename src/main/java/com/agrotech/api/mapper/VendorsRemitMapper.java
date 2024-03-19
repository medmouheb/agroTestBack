package com.agrotech.api.mapper;

import com.agrotech.api.dto.VendorsRemitDto;
import com.agrotech.api.model.VendorsRemit;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper

public interface VendorsRemitMapper extends BaseMapper<VendorsRemitDto, VendorsRemit>{
}

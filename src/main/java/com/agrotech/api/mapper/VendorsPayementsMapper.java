package com.agrotech.api.mapper;

import com.agrotech.api.dto.VendorsPayementDto;
import com.agrotech.api.model.VendorsPayments;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper()
@Component
public interface VendorsPayementsMapper extends BaseMapper<VendorsPayementDto, VendorsPayments>{
}

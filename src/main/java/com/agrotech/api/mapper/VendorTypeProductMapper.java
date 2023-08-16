package com.agrotech.api.mapper;


import com.agrotech.api.dto.VendorTypeProductDto;
import com.agrotech.api.model.VendorTypeProduct;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;


@Mapper
@Component
public interface VendorTypeProductMapper extends BaseMapper<VendorTypeProductDto, VendorTypeProduct>{

}

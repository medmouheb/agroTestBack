package com.agrotech.api.mapper;

import com.agrotech.api.dto.DeliveryDto;
import com.agrotech.api.model.Delivery;
import org.mapstruct.Mapper;


@Mapper
public interface DeliveryMapper extends BaseMapper<DeliveryDto, Delivery>  {
}

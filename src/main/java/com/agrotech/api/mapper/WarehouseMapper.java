package com.agrotech.api.mapper;
import org.mapstruct.Mapper;

import com.agrotech.api.dto.WarehouseDto;
import com.agrotech.api.model.Warehouse;
import org.springframework.stereotype.Component;

@Mapper()
@Component

public interface WarehouseMapper extends BaseMapper<WarehouseDto, Warehouse> {
}

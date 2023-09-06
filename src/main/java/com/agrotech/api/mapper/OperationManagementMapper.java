package com.agrotech.api.mapper;

import com.agrotech.api.dto.OperationManagementDto;
import com.agrotech.api.model.OperationManagement;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface OperationManagementMapper extends BaseMapper<OperationManagementDto, OperationManagement>{
}

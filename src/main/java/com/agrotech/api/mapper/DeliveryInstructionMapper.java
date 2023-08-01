package com.agrotech.api.mapper;

import com.agrotech.api.dto.DeliveryInstructionDto;
import com.agrotech.api.model.DeliveryInstruction;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper()
@Component
public interface DeliveryInstructionMapper extends BaseMapper<DeliveryInstructionDto, DeliveryInstruction> {
}

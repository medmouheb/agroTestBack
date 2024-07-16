package com.agrotech.api.mapper;


import com.agrotech.api.dto.DeliveryNoteDto;
import com.agrotech.api.model.DeliveryNote;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component

public interface DeliveryNoteMapper extends BaseMapper<DeliveryNoteDto, DeliveryNote> {
}

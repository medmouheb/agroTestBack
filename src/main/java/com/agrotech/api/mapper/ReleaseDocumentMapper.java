package com.agrotech.api.mapper;

import com.agrotech.api.dto.DeliveryNoteDto;
import com.agrotech.api.dto.ReleaseDocumentDto;
import com.agrotech.api.model.DeliveryNote;
import com.agrotech.api.model.ReleaseDocument;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface ReleaseDocumentMapper extends BaseMapper<ReleaseDocumentDto, ReleaseDocument>  {
}

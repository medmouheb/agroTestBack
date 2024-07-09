package com.agrotech.api.mapper;

import com.agrotech.api.dto.DeliveryNoteDto;
import com.agrotech.api.dto.ReleaseDocumentDto;
import com.agrotech.api.model.DeliveryNote;
import com.agrotech.api.model.ReleaseDocument;
import org.mapstruct.Mapper;

@Mapper
public interface ReleaseDocumentMapper extends BaseMapper<ReleaseDocumentDto, ReleaseDocument>  {
}

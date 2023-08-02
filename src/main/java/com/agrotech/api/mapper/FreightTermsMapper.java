package com.agrotech.api.mapper;

import com.agrotech.api.dto.FreightTermsDto;
import com.agrotech.api.model.FreightTerms;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper()
@Component
public interface FreightTermsMapper extends BaseMapper<FreightTermsDto, FreightTerms>{
}

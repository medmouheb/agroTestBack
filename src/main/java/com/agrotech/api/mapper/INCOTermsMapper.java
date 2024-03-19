package com.agrotech.api.mapper;

import com.agrotech.api.dto.INCOTermsDto;
import com.agrotech.api.model.INCOTerms;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface INCOTermsMapper extends BaseMapper<INCOTermsDto, INCOTerms>{
}

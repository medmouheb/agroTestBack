package com.agrotech.api.mapper;

import com.agrotech.api.dto.TaxDto;
import com.agrotech.api.model.Tax;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper()
@Component
public interface TaxMapper extends BaseMapper<TaxDto, Tax> {
}

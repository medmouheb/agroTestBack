package com.agrotech.api.mapper;

import com.agrotech.api.dto.FactureDto;
import com.agrotech.api.model.Facture;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface FactureMapper extends BaseMapper<FactureDto, Facture> {
}

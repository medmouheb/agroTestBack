package com.agrotech.api.mapper;


import com.agrotech.api.dto.DevisDto;
import com.agrotech.api.model.Devis;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface DevisMapper extends BaseMapper<DevisDto, Devis> {
}

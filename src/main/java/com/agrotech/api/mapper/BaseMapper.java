package com.agrotech.api.mapper;

import org.mapstruct.BeanMapping;
import org.mapstruct.MappingTarget;
import org.mapstruct.Named;
import org.mapstruct.NullValuePropertyMappingStrategy;

/**
 * Contract for a generic dto to entity mapper.
 *
 * @param <DTO> - DTO type parameter.
 * @param <ENTITY> - Entity type parameter.
 */

 
public interface BaseMapper<DTO, ENTITY> {

    ENTITY toEntity(DTO dto);
    
    @BeanMapping(ignoreByDefault = false)
    DTO toDto(ENTITY entity);

    @Named("partialUpdate")
    @BeanMapping(nullValuePropertyMappingStrategy = NullValuePropertyMappingStrategy.IGNORE)
    void partialUpdate(@MappingTarget ENTITY entity, DTO dto);
}

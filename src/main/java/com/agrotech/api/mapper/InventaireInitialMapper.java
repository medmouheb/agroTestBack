package com.agrotech.api.mapper;

import com.agrotech.api.dto.InventaireInitialDto;
import com.agrotech.api.model.InventaireInitial;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface InventaireInitialMapper extends  BaseMapper<InventaireInitialDto, InventaireInitial>{
}

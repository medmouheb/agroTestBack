package com.agrotech.api.mapper;


import org.mapstruct.Mapper;

import com.agrotech.api.dto.FermeDto;
import com.agrotech.api.model.Ferme;
import org.springframework.stereotype.Component;

@Mapper(uses = {
        WarehouseMapper.class,
        FournisseurMapper.class

})
@Component

public interface FermeMapper extends BaseMapper<FermeDto, Ferme> {

}

package com.agrotech.api.mapper;

import com.agrotech.api.dto.FacilityDetailsDto;
import com.agrotech.api.model.FacilityDetails;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component

public interface FacilityDetailsMapper extends BaseMapper<FacilityDetailsDto, FacilityDetails>{

}

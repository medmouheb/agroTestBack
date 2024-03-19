package com.agrotech.api.mapper;


import com.agrotech.api.dto.CropDTO;
import com.agrotech.api.model.Crop;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
public interface CropMapper extends BaseMapper<CropDTO, Crop>  {
}

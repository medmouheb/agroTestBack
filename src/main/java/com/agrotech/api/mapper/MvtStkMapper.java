package com.agrotech.api.mapper;

import org.mapstruct.Mapper;

import com.agrotech.api.dto.MvtStkDto;
import com.agrotech.api.model.MvtStk;
import org.springframework.stereotype.Component;

@Mapper()
public interface MvtStkMapper extends BaseMapper<MvtStkDto, MvtStk>{

}

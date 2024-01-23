package com.agrotech.api.mapper;


import com.agrotech.api.dto.BuyDto;
import com.agrotech.api.model.Buy;
import org.mapstruct.Mapper;
import org.springframework.stereotype.Component;

@Mapper
@Component
public interface BuyMapper  extends BaseMapper<BuyDto, Buy> {

}

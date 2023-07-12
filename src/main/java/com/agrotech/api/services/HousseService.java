package com.agrotech.api.services;

import com.agrotech.api.dto.HousseDto;
import com.agrotech.api.exceptions.NotFoundException;


public interface HousseService extends BaseService<HousseDto, String>{
    HousseDto findByCode(String code) throws NotFoundException;

}

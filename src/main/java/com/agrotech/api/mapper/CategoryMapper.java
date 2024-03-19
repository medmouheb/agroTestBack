package com.agrotech.api.mapper;

import org.mapstruct.Mapper;

import com.agrotech.api.dto.CategoryDto;
import com.agrotech.api.model.Category;
import org.springframework.stereotype.Component;


@Mapper()
public interface CategoryMapper extends  BaseMapper<CategoryDto, Category>{

}  

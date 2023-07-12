package com.agrotech.api.services;

import org.springframework.data.domain.Page;

import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.CategoryDto;
import com.agrotech.api.dto.GrowoutDto;
import com.agrotech.api.exceptions.NotFoundException;

public interface CategoryService  extends BaseService<CategoryDto, String>{

	CategoryDto findByCode(String code) throws NotFoundException;

//	public void archive(String id) throws NotFoundException;
//
//	public Page<CategoryDto> findPage(int pageSize, int pageNumber, String filter);
//
//	public void setNotArchive(String id) throws NotFoundException;
//
//	public Page<CategoryDto> findArchivedPage(int pageSize, int pageNumber, String filter);

}

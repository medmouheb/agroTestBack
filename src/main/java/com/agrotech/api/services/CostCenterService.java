package com.agrotech.api.services;

import com.agrotech.api.model.CostCenter;
import org.springframework.data.domain.Page;

import com.agrotech.api.dto.CostCenterDto;
import com.agrotech.api.dto.GrowoutDto;
import com.agrotech.api.exceptions.NotFoundException;

public interface CostCenterService extends BaseService<CostCenterDto, String>{
	
	CostCenterDto findByCode(String code ) throws NotFoundException;
	public void archive(String id) throws NotFoundException;

	public void setNotArchive(String id) throws NotFoundException;

	public Page<CostCenterDto> findArchivedPage(int pageSize, int pageNumber, String filter);
	public Page<CostCenter> findPage1(int pageSize, int pageNumber, String filter);
	public Page<CostCenter> findArchivedPage1(int pageSize, int pageNumber, String filter);

}

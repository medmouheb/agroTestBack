package com.agrotech.api.services;


import com.agrotech.api.model.Division;
import org.springframework.data.domain.Page;


import com.agrotech.api.dto.DivisionDTO;
import com.agrotech.api.exceptions.NotFoundException;

public interface DivisionService extends BaseService<DivisionDTO, String> {

	DivisionDTO findByCode(String code) throws NotFoundException;

	public void archive(String id) throws NotFoundException;

	public Page<DivisionDTO> findPage(int pageSize, int pageNumber, String filter);

	public void setNotArchive(String id) throws NotFoundException;

	public Page<DivisionDTO> findArchivedPage(int pageSize, int pageNumber, String filter);
	public Page<Division> findArchivedPage1(int pageSize, int pageNumber, String filter);

	public Page<Division> findPage1(int pageSize, int pageNumber, String filter);
}

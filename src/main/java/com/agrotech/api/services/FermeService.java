package com.agrotech.api.services;

import com.agrotech.api.model.Currency;
import com.agrotech.api.model.Ferme;
import org.springframework.data.domain.Page;

import com.agrotech.api.dto.DivisionDTO;
import com.agrotech.api.dto.FermeDto;
import com.agrotech.api.exceptions.NotFoundException;

public interface FermeService extends BaseService<FermeDto, String> {
	Ferme saveferme(Ferme ferme);
	FermeDto findByCode(String code) throws NotFoundException;

	public void archive(String id) throws NotFoundException;

	public Page<FermeDto> findPage(int pageSize, int pageNumber, String filter);

	public void setNotArchive(String id) throws NotFoundException;
	public Page<Ferme> findArchivedPage1(int pageSize, int pageNumber, String filter);
	Ferme findByname(String name)throws NotFoundException;
	public Page<Ferme> findPage1(int pageSize, int pageNumber, String filter);
//	public Page<FermeDto> findArchivedPage(int pageSize, int pageNumber, String filter);
}

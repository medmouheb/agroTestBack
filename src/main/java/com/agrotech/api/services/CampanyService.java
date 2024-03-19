package com.agrotech.api.services;

import com.agrotech.api.model.Campany;
import org.springframework.data.domain.Page;

import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.exceptions.NotFoundException;

import java.util.List;

public interface CampanyService extends BaseService<CampanyDto, String> {

	CampanyDto findByCode(String code) throws NotFoundException;
		Page<CampanyDto> findPage1(int pageSize, int pageNumber, String filter) ;
	Page<Campany> getpages(int pageSize, int pageNumber, String filter) ;
	Page<Campany> getpages1(int pageSize, int pageNumber, String filter, String farmername) ;
	Page<Campany> getpagesarchive(int pageSize, int pageNumber, String filter) ;
	Page<Campany> getpagesarchive1(int pageSize, int pageNumber, String filter, String farmername) ;

	public void archive(String id) throws NotFoundException;

	// Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

	public void setNotArchive(String id) throws NotFoundException;
	  Campany findByname(String name)throws NotFoundException;
	public Page<CampanyDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
	public Page<CampanyDto> findArchivedPage(int pageSize, int pageNumber, String filter);

	List<Campany> findBynamee()throws NotFoundException;
}

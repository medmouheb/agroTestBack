package com.agrotech.api.services;

import com.agrotech.api.dto.SalesDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Sales;
import org.springframework.data.domain.Page;

import java.util.List;


public interface  SalesServices extends BaseService<SalesDto, String>{
	Sales saves (Sales sales);
    SalesDto findByCode(String code, String farmer) throws NotFoundException;
//    public void archive(String id) throws NotFoundException;

    List<SalesDto> findAllByfarmer(String farmer);

    public Page<SalesDto> findPage(int pageSize, int pageNumber, String filter);

//    public void setNotArchive(String id) throws NotFoundException;
//
//    public Page<SalesDto> findArchivedPage(int pageSize, int pageNumber, String filter);

    public void archive(String id) throws NotFoundException;
//    public Page<SalesSkuDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;

    public Page<SalesDto> findArchivedPage(int pageSize, int pageNumber, String filter);

    public Page<Sales> findPage1(int pageSize, int pageNumber, String filter);
    public Page<Sales> findPage1Farmer(String farmer,int pageSize, int pageNumber, String filter);
    public Page<Sales> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<Sales> findArchivedPage1Farmer(String farmer,int pageSize, int pageNumber, String filter);
    Sales findByname(String name)throws NotFoundException;
}



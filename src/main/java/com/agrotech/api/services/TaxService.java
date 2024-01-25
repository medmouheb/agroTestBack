package com.agrotech.api.services;

import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.TaxDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.Tax;
import org.springframework.data.domain.Page;

import java.util.List;

public interface TaxService extends BaseService<TaxDto, String> {

    TaxDto findByCode(String code) throws NotFoundException;
    Page<TaxDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<Tax> getpages(int pageSize, int pageNumber, String filter) ;
    Page<Tax> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    Tax findByname(String name)throws NotFoundException;
    public Page<TaxDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<TaxDto> findArchivedPage(int pageSize, int pageNumber, String filter);

    List<Tax> findBynamee()throws NotFoundException;
}

package com.agrotech.api.services;

import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.VendorTypesDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.VendorTypes;
import org.springframework.data.domain.Page;

public interface VendorTypesService extends BaseService<VendorTypesDto,String>{


    VendorTypesDto findById(String id) throws NotFoundException;
    Page<VendorTypesDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<VendorTypes> getpages(int pageSize, int pageNumber, String filter) ;
    Page<VendorTypes> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    //VendorTypes findByname(String name)throws NotFoundException;
    public Page<VendorTypesDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<VendorTypesDto> findArchivedPage(int pageSize, int pageNumber, String filter);
}


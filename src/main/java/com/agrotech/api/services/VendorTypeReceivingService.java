package com.agrotech.api.services;

import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.VendorTypeReceivingDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.VendorTypeReceiving;
import org.springframework.data.domain.Page;

public interface VendorTypeReceivingService extends BaseService<VendorTypeReceivingDto,String>{


    VendorTypeReceivingDto findById(String code) throws NotFoundException;
    Page<VendorTypeReceivingDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<VendorTypeReceiving> getpages(int pageSize, int pageNumber, String filter) ;
    Page<VendorTypeReceiving> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    //VendorTypeReceiving findByname(String name)throws NotFoundException;
    public Page<VendorTypeReceivingDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<VendorTypeReceivingDto> findArchivedPage(int pageSize, int pageNumber, String filter);
}


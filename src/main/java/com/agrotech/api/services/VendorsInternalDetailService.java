package com.agrotech.api.services;

import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.VendorsInternalDetailsDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.VendorsInternalDetails;
import org.springframework.data.domain.Page;

public interface VendorsInternalDetailService extends BaseService<VendorsInternalDetailsDto,String>{

    VendorsInternalDetailsDto findById(String id) throws NotFoundException;
    Page<VendorsInternalDetailsDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<VendorsInternalDetails> getpages(int pageSize, int pageNumber, String filter) ;
    Page<VendorsInternalDetails> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
   // VendorsInternalDetails findByname(String name)throws NotFoundException;
    public Page<VendorsInternalDetailsDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<VendorsInternalDetailsDto> findArchivedPage(int pageSize, int pageNumber, String filter);
}

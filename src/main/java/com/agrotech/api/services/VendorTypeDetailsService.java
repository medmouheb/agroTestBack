package com.agrotech.api.services;

import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.VendorTypeDetailsDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.VendorTypeDetails;
import org.springframework.data.domain.Page;

public interface VendorTypeDetailsService extends BaseService<VendorTypeDetailsDto, String>{

    VendorTypeDetailsDto findById(String code) throws NotFoundException;
    Page<VendorTypeDetailsDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<VendorTypeDetails> getpages(int pageSize, int pageNumber, String filter) ;
    Page<VendorTypeDetails> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    VendorTypeDetails findByOrderLine(String OrderLine)throws NotFoundException;
    public Page<VendorTypeDetailsDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<VendorTypeDetailsDto> findArchivedPage(int pageSize, int pageNumber, String filter);

}

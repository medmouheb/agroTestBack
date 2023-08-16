package com.agrotech.api.services;

import com.agrotech.api.dto.VendorsDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Vendors;
import org.springframework.data.domain.Page;

public interface VendorsService extends BaseService<VendorsDto,String>{

    VendorsDto findByVendorCode(String VendorCode) throws NotFoundException;
    Page<VendorsDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<Vendors> getpages(int pageSize, int pageNumber, String filter) ;
    Page<Vendors> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    Vendors findByVendorName01(String VendorName01)throws NotFoundException;
    public Page<VendorsDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<VendorsDto> findArchivedPage(int pageSize, int pageNumber, String filter);

}

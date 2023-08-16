package com.agrotech.api.services;

import com.agrotech.api.dto.VendorsDto;
import com.agrotech.api.dto.VendorsShippingDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Vendors;
import com.agrotech.api.model.VendorsShipping;
import org.springframework.data.domain.Page;

public interface VendorsShippingService extends BaseService<VendorsShippingDto,String>{


    VendorsShippingDto findByShippingLocationCode(String ShippingLocationCode) throws NotFoundException;
    Page<VendorsShippingDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<VendorsShipping> getpages(int pageSize, int pageNumber, String filter) ;
    Page<VendorsShipping> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    VendorsShipping findByShippingLocationName(String ShippingLocationName)throws NotFoundException;
    public Page<VendorsShippingDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<VendorsShippingDto> findArchivedPage(int pageSize, int pageNumber, String filter);

}

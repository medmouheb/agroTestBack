package com.agrotech.api.services;

import com.agrotech.api.dto.VendorTypeFeedDto;
import com.agrotech.api.dto.VendorTypeFreightDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.VendorTypeFeed;
import com.agrotech.api.model.VendorTypeFreight;
import org.springframework.data.domain.Page;

public interface VendorTypeFreightService extends BaseService<VendorTypeFreightDto,String>{


    VendorTypeFreightDto findByFreightTermCode(String FreightTermCode) throws NotFoundException;
    Page<VendorTypeFreightDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<VendorTypeFreight> getpages(int pageSize, int pageNumber, String filter) ;
    Page<VendorTypeFreight> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    VendorTypeFreight findByFreightTermName(String FreightTermName)throws NotFoundException;
    public Page<VendorTypeFreightDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<VendorTypeFreightDto> findArchivedPage(int pageSize, int pageNumber, String filter);


}

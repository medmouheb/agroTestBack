package com.agrotech.api.services;

import com.agrotech.api.dto.VendorTypeDetailsDto;
import com.agrotech.api.dto.VendorTypeFeedDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.VendorTypeDetails;
import com.agrotech.api.model.VendorTypeFeed;
import org.springframework.data.domain.Page;

public interface VendorTypeFeedService extends BaseService<VendorTypeFeedDto,String>{


    VendorTypeFeedDto findById(String code) throws NotFoundException;
    Page<VendorTypeFeedDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<VendorTypeFeed> getpages(int pageSize, int pageNumber, String filter) ;
    Page<VendorTypeFeed> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    VendorTypeFeed findByPOState(String POState)throws NotFoundException;
    public Page<VendorTypeFeedDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<VendorTypeFeedDto> findArchivedPage(int pageSize, int pageNumber, String filter);


}

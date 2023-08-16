package com.agrotech.api.services;

import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.VendorTypePODetailsDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.VendorTypePODetails;
import org.springframework.data.domain.Page;

public interface VendorTypePODetailsService extends BaseService<VendorTypePODetailsDto, String>{

    VendorTypePODetailsDto findById(String id) throws NotFoundException;
    Page<VendorTypePODetailsDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<VendorTypePODetails> getpages(int pageSize, int pageNumber, String filter) ;
    Page<VendorTypePODetails> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    VendorTypePODetails findByBuyerName(String BuyerName)throws NotFoundException;
    public Page<VendorTypePODetailsDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<VendorTypePODetailsDto> findArchivedPage(int pageSize, int pageNumber, String filter);

}

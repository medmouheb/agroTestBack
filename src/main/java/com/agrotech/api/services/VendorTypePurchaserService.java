package com.agrotech.api.services;

import com.agrotech.api.dto.VendorTypePurchaserDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.VendorTypePurchaser;
import org.springframework.data.domain.Page;

public interface VendorTypePurchaserService extends BaseService<VendorTypePurchaserDto,String>{

    VendorTypePurchaserDto findById(String id) throws NotFoundException;
    Page<VendorTypePurchaserDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<VendorTypePurchaser> getpages(int pageSize, int pageNumber, String filter) ;
    Page<VendorTypePurchaser> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    //VendorTypePODetails findByBuyerName(String BuyerName)throws NotFoundException;
    public Page<VendorTypePurchaserDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<VendorTypePurchaserDto> findArchivedPage(int pageSize, int pageNumber, String filter);


}

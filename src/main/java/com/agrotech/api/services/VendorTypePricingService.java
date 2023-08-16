package com.agrotech.api.services;

import com.agrotech.api.dto.VendorTypePricingDto;
import com.agrotech.api.dto.VendorTypeProductDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.VendorTypePricing;
import com.agrotech.api.model.VendorTypeProduct;
import org.springframework.data.domain.Page;

public interface VendorTypePricingService extends BaseService<VendorTypePricingDto,String>{



    VendorTypePricingDto findByCurrencyCode(String CurrencyCode) throws NotFoundException;
    Page<VendorTypePricingDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<VendorTypePricing> getpages(int pageSize, int pageNumber, String filter) ;
    Page<VendorTypePricing> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    VendorTypePricing findByCurrencyName(String CurrencyName)throws NotFoundException;
    public Page<VendorTypePricingDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<VendorTypePricingDto> findArchivedPage(int pageSize, int pageNumber, String filter);

}

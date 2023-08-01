package com.agrotech.api.services;

import com.agrotech.api.dto.VendorSKUDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.Currency;
import com.agrotech.api.model.SalesSKU;
import com.agrotech.api.model.VendorSKU;
import org.springframework.data.domain.Page;

 public interface VendorSKUService extends BaseService<VendorSKUDto, String>{

    VendorSKUDto findByCode(String vendorSKUCode) throws NotFoundException;


    public void archive(String id) throws NotFoundException;

//    public Page<VendorSKUDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;

    public Page<VendorSKUDto> findArchivedPage(int pageSize, int pageNumber, String filter);

    public Page<VendorSKU> findPage1(int pageSize, int pageNumber, String filter);
    public Page<VendorSKU> findArchivedPage1(int pageSize, int pageNumber, String filter);

VendorSKU findByname(String name)throws NotFoundException;
 }

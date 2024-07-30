package com.agrotech.api.services;

import com.agrotech.api.dto.VendorSKUDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.VendorSKU;
import org.springframework.data.domain.Page;

import java.util.List;

public interface VendorSKUService extends BaseService<VendorSKUDto, String>{

    VendorSKUDto findByCode(String vendorSKUCode, String farmer) throws NotFoundException;


    public void archive(String id) throws NotFoundException;

//    public Page<VendorSKUDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;

    public Page<VendorSKUDto> findArchivedPage(int pageSize, int pageNumber, String filter);

    List<VendorSKUDto> findAllByfarmer(String farmer);

    public Page<VendorSKU> findPage1(int pageSize, int pageNumber, String filter);
     public Page<VendorSKU> findPage1Farmer(String farmer,int pageSize, int pageNumber, String filter);
     public Page<VendorSKU> findArchivedPage1(int pageSize, int pageNumber, String filter);
     public Page<VendorSKU> findArchivedPage1Farmer(String farmer,int pageSize, int pageNumber, String filter);

VendorSKU findByname(String name)throws NotFoundException;
 }

package com.agrotech.api.services;

import com.agrotech.api.dto.SalesSkuDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Currency;
import com.agrotech.api.model.SalesSKU;
import org.springframework.data.domain.Page;

public interface SalesSkuServices extends BaseService<SalesSkuDto, String>{

    SalesSkuDto findByCode(String sailorCode) throws NotFoundException;
    public void archive(String id) throws NotFoundException;


 //public Page<SalesSkuDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;

    public Page<SalesSkuDto> findArchivedPage(int pageSize, int pageNumber, String filter);
    public Page<SalesSKU> findPage1(int pageSize, int pageNumber, String filter);
    public Page<SalesSKU> findArchivedPage1(int pageSize, int pageNumber, String filter);
 SalesSKU findByname(String name)throws NotFoundException;
}

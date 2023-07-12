package com.agrotech.api.services;

import com.agrotech.api.dto.SalesDto;
import com.agrotech.api.dto.SalesSkuDto;
import com.agrotech.api.dto.WillayaDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Currency;
import com.agrotech.api.model.Produit;
import com.agrotech.api.model.Sales;
import com.agrotech.api.model.SalesSKU;
import org.springframework.data.domain.Page;


public interface  SalesServices extends BaseService<SalesDto, String>{
	
    SalesDto findByCode(String code) throws NotFoundException;
//    public void archive(String id) throws NotFoundException;

    public Page<SalesDto> findPage(int pageSize, int pageNumber, String filter);

//    public void setNotArchive(String id) throws NotFoundException;
//
//    public Page<SalesDto> findArchivedPage(int pageSize, int pageNumber, String filter);

    public void archive(String id) throws NotFoundException;
//    public Page<SalesSkuDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;

    public Page<SalesDto> findArchivedPage(int pageSize, int pageNumber, String filter);

    public Page<Sales> findPage1(int pageSize, int pageNumber, String filter);
    public Page<Sales> findArchivedPage1(int pageSize, int pageNumber, String filter);

}



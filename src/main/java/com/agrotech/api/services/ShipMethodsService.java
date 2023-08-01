package com.agrotech.api.services;


import com.agrotech.api.dto.ShipMethodsDto;
import com.agrotech.api.exceptions.NotFoundException;

import com.agrotech.api.model.ShipMethods;
import org.springframework.data.domain.Page;

public interface ShipMethodsService extends BaseService<ShipMethodsDto, String>  {
    ShipMethodsDto findByCode(String code) throws NotFoundException;
    Page<ShipMethodsDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<ShipMethods> getpages(int pageSize, int pageNumber, String filter) ;
    Page<ShipMethods> getpagesarchive(int pageSize, int pageNumber, String filter) ;
    public void archive(String id) throws NotFoundException;
    public void setNotArchive(String id) throws NotFoundException;
    ShipMethods findByname(String name)throws NotFoundException;
    public Page<ShipMethodsDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<ShipMethodsDto> findArchivedPage(int pageSize, int pageNumber, String filter);
}

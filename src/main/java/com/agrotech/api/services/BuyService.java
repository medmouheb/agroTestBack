package com.agrotech.api.services;

import com.agrotech.api.dto.BuyDto;
import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Buy;
import com.agrotech.api.model.Campany;
import org.springframework.data.domain.Page;

import java.util.List;

public interface BuyService extends BaseService<BuyDto,String> {

    BuyDto findByCode(String code) throws NotFoundException;

    Page<BuyDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<Buy> getpages(int pageSize, int pageNumber, String filter) ;
    Page<Buy> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    public void setNotArchive(String id) throws NotFoundException;
    public Page<BuyDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<BuyDto> findArchivedPage(int pageSize, int pageNumber, String filter);

}



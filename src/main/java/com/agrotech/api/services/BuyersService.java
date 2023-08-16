package com.agrotech.api.services;

import com.agrotech.api.dto.BuyersDto;
import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Buyers;
import com.agrotech.api.model.Campany;
import org.springframework.data.domain.Page;

public interface BuyersService extends BaseService<BuyersDto,String>{


    BuyersDto findByBuyersCode(String BuyersCode) throws NotFoundException;
    Page<BuyersDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<Buyers> getpages(int pageSize, int pageNumber, String filter) ;
    Page<Buyers> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    Buyers findByBuyersName(String BuyersName)throws NotFoundException;
    public Page<BuyersDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<BuyersDto> findArchivedPage(int pageSize, int pageNumber, String filter);

}

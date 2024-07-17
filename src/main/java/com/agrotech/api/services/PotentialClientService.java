package com.agrotech.api.services;

import com.agrotech.api.dto.BuyersDto;
import com.agrotech.api.dto.PotentialClientDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Buyers;
import com.agrotech.api.model.PotentialClient;
import org.springframework.data.domain.Page;

import java.util.List;

public interface PotentialClientService extends BaseService<PotentialClientDto,String>{

    List<PotentialClientDto> findByFarmer(String farmer ) throws NotFoundException;

    PotentialClientDto findByBuyersCode(String BuyersCode , String farmer ) throws NotFoundException;
    Page<PotentialClientDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<PotentialClient> getpages(int pageSize, int pageNumber, String filter) ;
    Page<PotentialClient> getpagesFarmer(String farmer,int pageSize, int pageNumber, String filter) ;
    Page<PotentialClient> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    PotentialClient findByBuyersName(String BuyersName)throws NotFoundException;
    public Page<PotentialClientDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<PotentialClientDto> findArchivedPage(int pageSize, int pageNumber, String filter);

}


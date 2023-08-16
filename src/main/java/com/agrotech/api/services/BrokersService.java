package com.agrotech.api.services;

import com.agrotech.api.dto.BrokersDto;
import com.agrotech.api.dto.BuyersDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Brokers;
import com.agrotech.api.model.Buyers;
import org.springframework.data.domain.Page;

public interface BrokersService extends BaseService<BrokersDto,String>{


    BrokersDto findByBrokerCode(String BrokerCode) throws NotFoundException;
    Page<BrokersDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<Brokers> getpages(int pageSize, int pageNumber, String filter) ;
    Page<Brokers> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    Brokers findByBrokerName(String BrokerName)throws NotFoundException;
    public Page<BrokersDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<BrokersDto> findArchivedPage(int pageSize, int pageNumber, String filter);


}

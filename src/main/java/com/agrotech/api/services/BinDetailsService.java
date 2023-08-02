package com.agrotech.api.services;

import com.agrotech.api.dto.BinDetailsDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.BinDetails;
import org.springframework.data.domain.Page;

public interface BinDetailsService extends BaseService<BinDetailsDto, String>{

    BinDetailsDto findByBin(Number bin) throws NotFoundException;
    Page<BinDetailsDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<BinDetails> getpages(int pageSize, int pageNumber, String filter) ;
    Page<BinDetails> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    BinDetails findByCapacity(Double capacity)throws NotFoundException;
    public Page<BinDetailsDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<BinDetailsDto> findArchivedPage(int pageSize, int pageNumber, String filter);
}

package com.agrotech.api.services;

import com.agrotech.api.dto.StockDTO;
import com.agrotech.api.dto.TaxDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Stock;
import com.agrotech.api.model.Tax;
import org.springframework.data.domain.Page;

import java.util.List;

public interface StockServices extends BaseService<StockDTO, String> {

    StockDTO findByCode(String code) throws NotFoundException;
    Page<StockDTO> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<Stock> getpages(int pageSize, int pageNumber, String filter) ;
    Page<Stock> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;


    public void setNotArchive(String id) throws NotFoundException;
    Stock findByname(String name)throws NotFoundException;
    public Page<StockDTO> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<StockDTO> findArchivedPage(int pageSize, int pageNumber, String filter);

    List<Stock> findBynamee()throws NotFoundException;
}

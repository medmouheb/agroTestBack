package com.agrotech.api.services;

import com.agrotech.api.dto.LogisticUnitDto;
import com.agrotech.api.dto.RapprochementDesStocksDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.LogisticUnit;
import com.agrotech.api.model.RapprochementDesStocks;
import org.springframework.data.domain.Page;

public interface RapprochementDesStocksService extends BaseService<RapprochementDesStocksDto,String>{

    RapprochementDesStocksDto findBynDeReference (String nDeReference) throws NotFoundException;
    Page<RapprochementDesStocksDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<RapprochementDesStocks> getpages(int pageSize, int pageNumber, String filter) ;
    Page<RapprochementDesStocks> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    RapprochementDesStocks findByNomDuProduits(String nomDuProduit)throws NotFoundException;

    public Page<RapprochementDesStocksDto> findArchivedPage1(int pageSize, int pageNumber, String filter);

    public Page<RapprochementDesStocksDto> findArchivedPage(int pageSize, int pageNumber, String filter);
}
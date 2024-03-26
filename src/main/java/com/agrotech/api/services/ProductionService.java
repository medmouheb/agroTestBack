package com.agrotech.api.services;

import com.agrotech.api.dto.ProductionDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Production;
import org.springframework.data.domain.Page;

public interface ProductionService  extends BaseService<ProductionDto, String> {
    ProductionDto findByName(String code) throws NotFoundException;


    Page<Production> getpages(int pageSize, int pageNumber, String filter, String farmername);
    Page<Production> getpages1(int pageSize, int pageNumber, String filter );

    Page<Production> getpagesarchive(int pageSize, int pageNumber, String filter);
    Page<Production> getpagesarchive1(int pageSize, int pageNumber, String filter, String farmername);

    public void archive(String id) throws NotFoundException;


    public void setNotArchive(String id) throws NotFoundException;


}

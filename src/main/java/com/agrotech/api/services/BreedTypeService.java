package com.agrotech.api.services;

import com.agrotech.api.dto.BreedTypeDto;
import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.BreedType;
import com.agrotech.api.model.Campany;
import org.springframework.data.domain.Page;

public interface BreedTypeService extends  BaseService<BreedTypeDto,String>{
    BreedTypeDto findByBreedTypeCode(String BreedTypeCode) throws NotFoundException;
    Page<BreedTypeDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<BreedType> getpages(int pageSize, int pageNumber, String filter) ;
    Page<BreedType> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    BreedType findByBreedTypeName(String BreedTypeName)throws NotFoundException;
    public Page<BreedTypeDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<BreedTypeDto> findArchivedPage(int pageSize, int pageNumber, String filter);
}

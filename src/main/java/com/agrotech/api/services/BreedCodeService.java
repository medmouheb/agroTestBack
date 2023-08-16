package com.agrotech.api.services;

import com.agrotech.api.dto.BreedCodeDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.BreedCode;
import org.springframework.data.domain.Page;

public interface BreedCodeService extends BaseService<BreedCodeDto,String>{

    BreedCodeDto findByBreedCode(String BreedCode) throws NotFoundException;
    Page<BreedCodeDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<BreedCode> getpages(int pageSize, int pageNumber, String filter) ;
    Page<BreedCode> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;

    BreedCode findByBreedCodeName(String BreedCodeName) throws NotFoundException;

    public Page<BreedCodeDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<BreedCodeDto> findArchivedPage(int pageSize, int pageNumber, String filter);
}

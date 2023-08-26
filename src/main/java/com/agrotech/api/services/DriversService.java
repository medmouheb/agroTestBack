package com.agrotech.api.services;

import com.agrotech.api.dto.DriversDto;
import com.agrotech.api.dto.LogisticUnitDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Drivers;
import com.agrotech.api.model.LogisticUnit;
import org.springframework.data.domain.Page;

public interface DriversService extends BaseService<DriversDto, String>{

    DriversDto findByCodeEmploye(String codeEmploye) throws NotFoundException;
    Page<DriversDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<Drivers> getpages(int pageSize, int pageNumber, String filter) ;
    Page<Drivers> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    Drivers findByNomDuChauffeur(String nomDuChauffeur)throws NotFoundException;
    public Page<DriversDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<DriversDto> findArchivedPage(int pageSize, int pageNumber, String filter);


}

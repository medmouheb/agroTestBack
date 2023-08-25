package com.agrotech.api.services;

import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.LogisticUnitDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.LogisticUnit;
import org.springframework.data.domain.Page;

import java.util.List;

public interface LogisticUnitService extends BaseService<LogisticUnitDto, String>{
    LogisticUnitDto findByLogisticCode(String LogisticCode) throws NotFoundException;
    Page<LogisticUnitDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<LogisticUnit> getpages(int pageSize, int pageNumber, String filter) ;
    Page<LogisticUnit> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    LogisticUnit findByLogisticName(String LogisticName)throws NotFoundException;
    public Page<LogisticUnitDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<LogisticUnitDto> findArchivedPage(int pageSize, int pageNumber, String filter);

    List<LogisticUnitDto> findByLogisticCodes() throws NotFoundException;
}

package com.agrotech.api.services;

import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.ReceptionDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.Reception;
import org.springframework.data.domain.Page;

public interface ReceptionService extends BaseService<ReceptionDto,String>{

    ReceptionDto findById(String id) throws NotFoundException;
    Page<ReceptionDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<Reception> getpages(int pageSize, int pageNumber, String filter) ;
    Page<Reception> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    //Campany findByname(String name)throws NotFoundException;
    public Page<ReceptionDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<ReceptionDto> findArchivedPage(int pageSize, int pageNumber, String filter);

}

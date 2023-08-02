package com.agrotech.api.services;

import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.VehicleTypeDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.VehicleType;
import org.springframework.data.domain.Page;

public interface VehicleTypeService extends BaseService<VehicleTypeDto,String> {
    VehicleTypeDto findByCode(String code) throws NotFoundException;
    Page<VehicleTypeDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<VehicleType> getpages(int pageSize, int pageNumber, String filter) ;
    Page<VehicleType> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    VehicleType findByname(String name)throws NotFoundException;
    public Page<VehicleTypeDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<VehicleTypeDto> findArchivedPage(int pageSize, int pageNumber, String filter);

}

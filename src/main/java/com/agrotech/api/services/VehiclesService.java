package com.agrotech.api.services;

import com.agrotech.api.dto.LogisticUnitDto;
import com.agrotech.api.dto.VehiclesDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.LogisticUnit;
import com.agrotech.api.model.Vehicles;
import org.springframework.data.domain.Page;

public interface VehiclesService extends  BaseService<VehiclesDto,String>{
    VehiclesDto findByCodeVehicle(String codeVehicule) throws NotFoundException;
    Page<VehiclesDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<Vehicles> getpages(int pageSize, int pageNumber, String filter) ;
    Page<Vehicles> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    Vehicles findByNomDuVehicule(String nomDuVehicule)throws NotFoundException;
    public Page<VehiclesDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<VehiclesDto> findArchivedPage(int pageSize, int pageNumber, String filter);
}

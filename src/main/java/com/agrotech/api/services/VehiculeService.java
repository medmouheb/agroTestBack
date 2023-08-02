package com.agrotech.api.services;

import com.agrotech.api.dto.VehiculeDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Vehicule;
import org.springframework.data.domain.Page;

public interface VehiculeService extends BaseService<VehiculeDto, String>{

    VehiculeDto findByVehiculeCode(String vehiculeCode) throws NotFoundException;
    Page<VehiculeDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<Vehicule> getpages(int pageSize, int pageNumber, String filter) ;
    Page<Vehicule> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;


    public void setNotArchive(String id) throws NotFoundException;
    Vehicule findByVehiculeName(String vehiculeName)throws NotFoundException;
    public Page<VehiculeDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<VehiculeDto> findArchivedPage(int pageSize, int pageNumber, String filter);
}

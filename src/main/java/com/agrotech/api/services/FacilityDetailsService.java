package com.agrotech.api.services;

import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.FacilityDetailsDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.FacilityDetails;
import org.springframework.data.domain.Page;

public interface FacilityDetailsService extends BaseService<FacilityDetailsDto, String> {

    FacilityDetailsDto findByFacilityID(String facilityID) throws NotFoundException;
    Page<FacilityDetailsDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<FacilityDetails> getpages(int pageSize, int pageNumber, String filter) ;
    Page<FacilityDetails> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    FacilityDetails findByFacilityName(String facilityName)throws NotFoundException;
    public Page<FacilityDetailsDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<FacilityDetailsDto> findArchivedPage(int pageSize, int pageNumber, String filter);
}

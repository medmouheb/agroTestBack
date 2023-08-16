package com.agrotech.api.services;

import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.VendorsContactInformationDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.VendorsContactInformation;
import org.springframework.data.domain.Page;

public interface VendorsContactInformationService extends BaseService<VendorsContactInformationDto, String>{


    VendorsContactInformationDto findByLabelCode(String LabelCode) throws NotFoundException;
    Page<VendorsContactInformationDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<VendorsContactInformation> getpages(int pageSize, int pageNumber, String filter) ;
    Page<VendorsContactInformation> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    VendorsContactInformation findByLabelName(String LabelName)throws NotFoundException;
    public Page<VendorsContactInformationDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<VendorsContactInformationDto> findArchivedPage(int pageSize, int pageNumber, String filter);

}


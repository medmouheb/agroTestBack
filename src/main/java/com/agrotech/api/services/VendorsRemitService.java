package com.agrotech.api.services;

import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.VendorsRemitDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.VendorsRemit;
import org.springframework.data.domain.Page;

public interface VendorsRemitService extends BaseService <VendorsRemitDto,String>{

    VendorsRemitDto findById(String id) throws NotFoundException;
    Page<VendorsRemitDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<VendorsRemit> getpages(int pageSize, int pageNumber, String filter) ;
    Page<VendorsRemit> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    VendorsRemit findByRemitToName(String RemitToName)throws NotFoundException;
    public Page<VendorsRemitDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<VendorsRemitDto> findArchivedPage(int pageSize, int pageNumber, String filter);
}

package com.agrotech.api.services;

import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.VendorsPayementDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.VendorsPayments;
import org.springframework.data.domain.Page;

public interface VendorsPaymentsService extends BaseService <VendorsPayementDto,String>{

    VendorsPayementDto findById(String code) throws NotFoundException;
    Page<VendorsPayementDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<VendorsPayments> getpages(int pageSize, int pageNumber, String filter) ;
    Page<VendorsPayments> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    //VendorsPayments findBy(String name)throws NotFoundException;
    public Page<VendorsPayementDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<VendorsPayementDto> findArchivedPage(int pageSize, int pageNumber, String filter);

}

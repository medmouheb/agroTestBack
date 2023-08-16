package com.agrotech.api.services;

import com.agrotech.api.dto.VendorTypeDetailsDto;
import com.agrotech.api.dto.VendorTypeProductDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.VendorTypeDetails;
import com.agrotech.api.model.VendorTypeProduct;
import org.springframework.data.domain.Page;

public interface VendorTypeProductService extends BaseService<VendorTypeProductDto, String>{



    VendorTypeProductDto findByProductCode(String ProductCode) throws NotFoundException;
    Page<VendorTypeProductDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<VendorTypeProduct> getpages(int pageSize, int pageNumber, String filter) ;
    Page<VendorTypeProduct> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    VendorTypeProduct findByProductName(String ProductName)throws NotFoundException;
    public Page<VendorTypeProductDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<VendorTypeProductDto> findArchivedPage(int pageSize, int pageNumber, String filter);

}

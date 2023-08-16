package com.agrotech.api.services;

import com.agrotech.api.dto.VendorTypeFreightDto;
import com.agrotech.api.dto.VendorTypeHistoryIInvoicesDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.VendorTypeFreight;
import com.agrotech.api.model.VendorTypeHistoryIInvoices;
import org.springframework.data.domain.Page;

public interface VendorTypeHistoryIInvoicesService extends BaseService<VendorTypeHistoryIInvoicesDto,String>{


    VendorTypeHistoryIInvoicesDto findByInvoiceCode(String InvoiceCode) throws NotFoundException;
    Page<VendorTypeHistoryIInvoicesDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<VendorTypeHistoryIInvoices> getpages(int pageSize, int pageNumber, String filter) ;
    Page<VendorTypeHistoryIInvoices> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    VendorTypeHistoryIInvoices findByInvoiceType(String InvoiceType)throws NotFoundException;
    public Page<VendorTypeHistoryIInvoicesDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<VendorTypeHistoryIInvoicesDto> findArchivedPage(int pageSize, int pageNumber, String filter);


}

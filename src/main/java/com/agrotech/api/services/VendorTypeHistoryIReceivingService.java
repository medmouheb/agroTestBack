package com.agrotech.api.services;

import com.agrotech.api.dto.CampanyDto;
import com.agrotech.api.dto.VendorTypeHistoryIReceivingDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Campany;
import com.agrotech.api.model.VendorTypeHistoryIReceiving;
import org.springframework.data.domain.Page;

public interface VendorTypeHistoryIReceivingService extends BaseService<VendorTypeHistoryIReceivingDto,String>{



    VendorTypeHistoryIReceivingDto findById(String id) throws NotFoundException;
    Page<VendorTypeHistoryIReceivingDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<VendorTypeHistoryIReceiving> getpages(int pageSize, int pageNumber, String filter) ;
    Page<VendorTypeHistoryIReceiving> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    VendorTypeHistoryIReceiving findByApprovalUserID(String ApprovalUserID)throws NotFoundException;
    public Page<VendorTypeHistoryIReceivingDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<VendorTypeHistoryIReceivingDto> findArchivedPage(int pageSize, int pageNumber, String filter);

}

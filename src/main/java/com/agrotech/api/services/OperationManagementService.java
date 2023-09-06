package com.agrotech.api.services;

import com.agrotech.api.dto.DriversDto;
import com.agrotech.api.dto.LogisticUnitDto;
import com.agrotech.api.dto.OperationManagementDto;
import com.agrotech.api.exceptions.NotFoundException;
import com.agrotech.api.model.Drivers;
import com.agrotech.api.model.LogisticUnit;
import com.agrotech.api.model.OperationManagement;
import org.springframework.data.domain.Page;

import java.util.List;

public interface OperationManagementService extends BaseService<OperationManagementDto,String>{
    OperationManagementDto findByOperationId(String OperationId) throws NotFoundException;
    Page<OperationManagementDto> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<OperationManagement> getpages(int pageSize, int pageNumber, String filter) ;
    Page<OperationManagement> getpagesarchive(int pageSize, int pageNumber, String filter) ;

    public void archive(String id) throws NotFoundException;

    // Page<CampanyDto> findPage(int pageSize, int pageNumber, String filter);

    public void setNotArchive(String id) throws NotFoundException;
    OperationManagement findByNomOperation(String nomOperation)throws NotFoundException;
    public Page<OperationManagementDto> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<OperationManagementDto> findArchivedPage(int pageSize, int pageNumber, String filter);

    List<OperationManagement> findByNomDuChauffeurs()throws NotFoundException;


}

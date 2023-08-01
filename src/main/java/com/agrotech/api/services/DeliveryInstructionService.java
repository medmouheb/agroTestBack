package com.agrotech.api.services;


import com.agrotech.api.dto.DeliveryInstructionDto;
import com.agrotech.api.exceptions.NotFoundException;

import com.agrotech.api.model.DeliveryInstruction;
import org.springframework.data.domain.Page;

public interface DeliveryInstructionService extends BaseService<DeliveryInstructionDto, String>{
    DeliveryInstructionDto findBytypeproduct(String producttype) throws NotFoundException;
    Page<DeliveryInstruction> findPage1(int pageSize, int pageNumber, String filter) ;
    Page<DeliveryInstruction> getpages(int pageSize, int pageNumber, String filter) ;
    Page<DeliveryInstruction> getpagesarchive(int pageSize, int pageNumber, String filter) ;
    public void archive(String id) throws NotFoundException;


    public void setNotArchive(String id) throws NotFoundException;
    public Page<DeliveryInstruction> findArchivedPage1(int pageSize, int pageNumber, String filter);
    public Page<DeliveryInstructionDto> findArchivedPage(int pageSize, int pageNumber, String filter);




}
